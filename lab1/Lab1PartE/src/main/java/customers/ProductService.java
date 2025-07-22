package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private ILogger logger;
    private IEmailSender emailSender;

    @Autowired
    public ProductService(ILogger logger, IEmailSender emailSender) {
        this.logger = logger;
        this.emailSender = emailSender;
    }

    public void addProduct(Product product) {
        // simulate saving to DB
        System.out.println("ProductService: saving product " + product.getName());
        logger.log("Product saved to DB: " + product.getName());

        // simulate sending email
        String message = "New product added: " + product.getName();
        emailSender.sendEmail("admin@store.com", message);
    }
}
