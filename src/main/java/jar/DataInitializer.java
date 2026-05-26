package jar;

import jar.model.User;
import jar.model.Wallet;
import jar.repository.UserRepository;
import jar.repository.WalletRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    // Direct injection of our repositories
    public DataInitializer(UserRepository userRepository, WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Only insert dummy data if the database users table is entirely empty
        if (userRepository.count() == 0) {
            
            // 1. Create Alice
            User alice = new User();
            alice.setName("Alice Smith");
            alice.setEmail("alice@example.com");
            alice = userRepository.save(alice);

            Wallet aliceWallet = new Wallet();
            aliceWallet.setUser(alice);
            aliceWallet.setBalance(new BigDecimal("1000.00")); // Clear financial precision
            aliceWallet.setCurrency("USD");
            aliceWallet = walletRepository.save(aliceWallet);

            // 2. Create Bob
            User bob = new User();
            bob.setName("Bob Jones");
            bob.setEmail("bob@example.com");
            bob = userRepository.save(bob);

            Wallet bobWallet = new Wallet();
            bobWallet.setUser(bob);
            bobWallet.setBalance(new BigDecimal("500.00"));
            bobWallet.setCurrency("USD");
            bobWallet = walletRepository.save(bobWallet);

            // 3. Print the generated UUIDs clearly to the console logs
            System.out.println("\n==================================================");
            System.out.println("   FINTECH SEED DATA INITIALIZED SUCCESSFULY!   ");
            System.out.println("==================================================");
            System.out.println("Alice Wallet ID: " + aliceWallet.getId());
            System.out.println("Bob Wallet ID:   " + bobWallet.getId());
            System.out.println("==================================================\n");
        }
    }
}