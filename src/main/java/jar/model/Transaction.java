package jar.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "source_wallet_id", nullable = false)
    private Wallet sourceWallet; // The wallet the money is leaving

    @ManyToOne
    @JoinColumn(name = "target_wallet_id", nullable = false)
    private Wallet targetWallet; // The wallet receiving the money

    @Column(nullable = false)
    private BigDecimal amount; // Precise monetary amount being moved

    @Column(nullable = false)
    private String status; // PENDING, SUCCESS, FAILED

    @Column(name = "idempotency_key", nullable = false, unique = true)
    private String idempotencyKey; // The "Senior Engineer" guardrail against double-spending

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // Automatically timestamps the ledger entry

    // Default constructor required by JPA
    public Transaction() {}

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Wallet getSourceWallet() {
        return sourceWallet;
    }

    public void setSourceWallet(Wallet sourceWallet) {
        this.sourceWallet = sourceWallet;
    }

    public Wallet getTargetWallet() {
        return targetWallet;
    }

    public void setTargetWallet(Wallet targetWallet) {
        this.targetWallet = targetWallet;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdempotencyKey() {
        return idempotencyKey; //  Fixed to return the actual String key
    }

    public void setIdempotencyKey(String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}