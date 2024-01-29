package project.ip.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.boot.CommandLineRunner;
import project.ip.ecommerce.entity.Role;
import project.ip.ecommerce.entity.User;
import project.ip.ecommerce.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "project.ip.ecommerce.repository")
@EntityScan(basePackages = "project.ip.ecommerce.entity")
public class EcommerceApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	public void run(String... args){
		User adminAccount = userRepository.findByRole(
			Role.ADMIN
		);
		if( null == adminAccount){
			User user = new User();
			user.setEmail("admins@gmail.com");
			user.setFirstName("admins");
			user.setLastName("admins");
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			user.setRole(Role.ADMIN);
			userRepository.save(user);
		}
	}

}
