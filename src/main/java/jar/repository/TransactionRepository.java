package jar.repository;

import jar.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    // Used by our idempotency check layer to prevent double-charging users
    Optional<Transaction> findByIdempotencyKey(String idempotencyKey);
}