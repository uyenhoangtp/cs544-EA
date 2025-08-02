package jmsCalculator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
@EnableJms
public class SenderApplication implements CommandLineRunner {
    @Autowired
    JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SenderApplication.class, args);
        context.close();
    }

    @Override
    public void run(String... args) throws Exception {

        Calculator calculator1 = new Calculator("+", 0);
        Calculator calculator2 = new Calculator("+", 7);
        Calculator calculator3 = new Calculator("+", 8);

        for (Calculator c: new Calculator[]{calculator1, calculator2, calculator3}){
            ObjectMapper objectMapper = new ObjectMapper();
            String calculatorAsString = objectMapper.writeValueAsString(c);

            System.out.println("Sending a JMS message:" + calculatorAsString);
            jmsTemplate.convertAndSend("testTopic", calculatorAsString);
        }

    }
}
