package jar.service;

import jar.model.Transaction;
import jar.model.Wallet;
import jar.repository.TransactionRepository;
import jar.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.UUID;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    // Dependency Injection via constructor
    public WalletService(WalletRepository walletRepository, TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    /**
     * The Core Financial Engine execution method.
     * @Transactional ensures atomic execution: if anything crashes inside this block, 
     * PostgreSQL completely rolls back all modifications as if nothing happened.
     */
    @Transactional
    public Transaction transferFunds(UUID sourceWalletId, UUID targetWalletId, BigDecimal amount, String idempotencyKey) {
        
        // Phase 1: Senior Engineer Idempotency Verification Guardrail
        var existingTransaction = transactionRepository.findByIdempotencyKey(idempotencyKey);
        if (existingTransaction.isPresent()) {
            return existingTransaction.get(); // Directly returns old transfer record if double clicked
        }

        // Phase 2: Lock the exact database rows using our custom Pessimistic Locking query
        Wallet sourceWallet = walletRepository.findByIdForUpdate(sourceWalletId)
                .orElseThrow(() -> new IllegalArgumentException("Source wallet not found"));
                
        Wallet targetWallet = walletRepository.findByIdForUpdate(targetWalletId)
                .orElseThrow(() -> new IllegalArgumentException("Target wallet not found"));

        // Phase 3: Strict Business Rule Valdiations
        if (sourceWallet.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient wallet funds for this transfer");
        }
        if (!sourceWallet.getCurrency().equals(targetWallet.getCurrency())) {
            throw new IllegalArgumentException("Currency mismatch error: Accounts must use identical currency types");
        }

        // Phase 4: Execute Balance Mutations
        sourceWallet.setBalance(sourceWallet.getBalance().subtract(amount));
        targetWallet.setBalance(targetWallet.getBalance().add(amount));

        walletRepository.save(sourceWallet);
        walletRepository.save(targetWallet);

        // Phase 5: Generate the Immutable Transaction Ledger Entry
        Transaction transaction = new Transaction();
        transaction.setSourceWallet(sourceWallet);
        transaction.setTargetWallet(targetWallet);
        transaction.setAmount(amount);
        transaction.setStatus("SUCCESS");
        transaction.setIdempotencyKey(idempotencyKey);

        return transactionRepository.save(transaction);
    }
}