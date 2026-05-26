package jar.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id; // Generates a secure, unguessable string ID [cite: 15]

    @Column(nullable = false, unique = true)
    private String email; // Ensures no two users can register with the same email

    @Column(nullable = false)
    private String name;

    // Default constructor required by JPA
    public User() {}

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}