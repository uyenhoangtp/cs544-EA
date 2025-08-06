package client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Collection;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		JwtApiClient client = new JwtApiClient();

		// Step 1: Signup John (if not already exists)
		client.signup("John", "Doe", "john@example.com", "john123");

		// Step 2: Login as John
		String userToken = client.signin("john@example.com", "john123");
		if (userToken != null) {
			client.callEndpoint("/api/all", userToken);
			client.callEndpoint("/api/users", userToken);
			client.callEndpoint("/api/admins", userToken); // expected to fail
		}

		System.out.println("-----");

		// Step 3: Login as Admin
		String adminToken = client.signin("admin@admin.com", "password");
		if (adminToken != null) {
			client.callEndpoint("/api/all", adminToken);
			client.callEndpoint("/api/users", adminToken);
			client.callEndpoint("/api/admins", adminToken);
		}

	}
}
