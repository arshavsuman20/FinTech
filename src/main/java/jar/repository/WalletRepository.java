package jar.repository;

import jar.model.Wallet;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import java.util.UUID;
import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {

    /**
     * The FinTech Golden Query.
     * Forces PostgreSQL to execute a 'SELECT ... FOR UPDATE' statement.
     * This locks the specific wallet row, making concurrent transfers wait in line
     * and completely eliminating race conditions.
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT w FROM Wallet w WHERE w.id = :id")
    Optional<Wallet> findByIdForUpdate(UUID id);
}