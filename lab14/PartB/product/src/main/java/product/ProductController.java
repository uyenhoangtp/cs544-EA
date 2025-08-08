package product;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ChatClient chatClient;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/init")
    public String initData(){
        productRepository.save(new Product("iPhone 15", "Latest iPhone with A16 chip", 999));
        productRepository.save(new Product("MacBook Pro", "M3 chip, 16GB RAM, 1TB SSD", 2399));
        productRepository.save(new Product("Apple Watch", "Health tracking wearable", 399));
        return "Sample data loaded.";
    }

    @GetMapping
    public ResponseEntity<?> getProductList(){
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/ask")
    public String askProduct (@RequestParam(value = "message") String message) throws IOException{
        List<Product> productList = productRepository.findAll();
        StringBuilder context = new StringBuilder("These are the products in our catalog:\n");
        for (Product p: productList){
            context.append("- ").append(p.getName())
                    .append(": ").append(p.getDescription())
                    .append(" ($").append(p.getPrice()).append(")\n");
        }

        return chatClient.prompt()
                .system(context.toString())
                .user(message)
                .call()
                .content();
    }
}
