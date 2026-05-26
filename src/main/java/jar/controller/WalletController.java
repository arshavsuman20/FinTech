package jar.controller;

import jar.dto.TransferRequest;
import jar.model.Transaction;
import jar.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private final WalletService walletService;

    // Dependency injection maps our core transaction logic here
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    /**
     * Endpoint to handle zero-loss fund transfers.
     * Consumes JSON payloads matching our TransferRequest structure.
     */
    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transfer(@RequestBody TransferRequest request) {
        Transaction transaction = walletService.transferFunds(
                request.getSourceWalletId(),
                request.getTargetWalletId(),
                request.getAmount(),
                request.getIdempotencyKey()
        );
        return ResponseEntity.ok(transaction);
    }
}