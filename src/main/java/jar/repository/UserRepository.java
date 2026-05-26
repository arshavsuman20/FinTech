package jar.repository;

import jar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, UUID> {
    // Allows us to look up profiles quickly during registration checks
    Optional<User> findByEmail(String email);
}