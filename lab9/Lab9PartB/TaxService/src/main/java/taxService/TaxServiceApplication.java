package taxService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class TaxServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaxServiceApplication.class, args);
    }
}
