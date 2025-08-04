package bank;

import bank.config.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Collection;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class Application implements CommandLineRunner {

	@Autowired
	private AppProperties appProperties;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("App Name: " + appProperties.getName());
		System.out.println("Version: " + appProperties.getVersion());
		System.out.println("Server URL: " + appProperties.getServer().getUrl());
		System.out.println("Server Name: " + appProperties.getServer().getName());

		System.out.println("User: " + appProperties.getUser().getFirstname() + " " + appProperties.getUser().getLastname());
		System.out.println("Username: " + appProperties.getUser().getUsername());
		System.out.println("Password: " + appProperties.getUser().getPassword());

		System.out.println("Countries: " + String.join(", ", appProperties.getCountries()));

	}
}
