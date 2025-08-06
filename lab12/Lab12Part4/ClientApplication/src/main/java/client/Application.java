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
		ApiClient client = new ApiClient();

		// Test with bob (sales)
		client.callEndpoint("/shop", "Bob", "Bob");
		client.callEndpoint("/orders", "Mary", "Mary");
		client.callEndpoint("/payments", "Bob", "Bob");

		System.out.println("-----");

		// Test with mary (finance)
		client.callEndpoint("/shop", "Mary", "Mary");
		client.callEndpoint("/orders", "Mary", "Mary");
		client.callEndpoint("/payments", "Mary", "Mary");

	}
}
