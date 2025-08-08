package pethealthcare;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pet-health")
public class PetHealthCareController {
    @Autowired
    private ChatClient chatClient;

    @GetMapping("/ask")
    public ChatResponse askPetHealthCareWithResponse (@RequestParam(value = "message", defaultValue = "Tell me a list of pet types in Pet Health Care") String message){
        return chatClient.prompt()
                .user(message)
                .call()
                .chatResponse();
    }

}
