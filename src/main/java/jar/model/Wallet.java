package jar.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Check;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "wallets")
@Check(constraints = "balance >= 0") // Our database-level protection against negative balances
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Links this wallet to a specific user profile

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO; // Financial precision using BigDecimal

    @Column(nullable = false, length = 3)
    private String currency; // E.g., "USD", "INR", "EUR"

    // Default constructor required by JPA
    public Wallet() {}

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}