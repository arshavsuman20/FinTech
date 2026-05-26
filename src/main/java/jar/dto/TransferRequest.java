package jar.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class TransferRequest {
    private UUID sourceWalletId;
    private UUID targetWalletId;
    private BigDecimal amount;
    private String idempotencyKey;

    // Default Constructor
    public TransferRequest() {}

    // Getters and Setters
    public UUID getSourceWalletId() {
        return sourceWalletId;
    }

    public void setSourceWalletId(UUID sourceWalletId) {
        this.sourceWalletId = sourceWalletId;
    }

    public UUID getTargetWalletId() {
        return targetWalletId;
    }

    public void setTargetWalletId(UUID targetWalletId) {
        this.targetWalletId = targetWalletId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }
}