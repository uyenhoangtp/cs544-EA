package kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Receiver3 {
    @KafkaListener(topics = "topicA2")
    public void receiveFromTopicA2( String message) {
        System.out.println("Receiver2 got message from topicA2: " + message);
    }
}
