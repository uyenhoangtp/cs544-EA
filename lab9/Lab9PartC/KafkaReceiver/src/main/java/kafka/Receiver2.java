package kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Receiver2 {
    @KafkaListener(topics = "topicA", groupId = "gid2")
    public void receiveFromTopicA(@Payload String message) {
        System.out.println("Receiver2 got message from topicA: " + message);
    }
}
