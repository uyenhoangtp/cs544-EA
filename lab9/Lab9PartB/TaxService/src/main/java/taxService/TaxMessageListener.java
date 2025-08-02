package taxService;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TaxMessageListener {
    @JmsListener(destination = "testTopic")
    public void receiveMessage(String message){
        System.out.println("JMS tax message: " + message);
    }
}
