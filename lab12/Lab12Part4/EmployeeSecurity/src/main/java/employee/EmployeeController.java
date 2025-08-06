package employee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @GetMapping("/shop")
    public ResponseEntity<?> getShop(){
        return new ResponseEntity<>("shop is accessible by everyone", HttpStatus.OK);
    }

    @GetMapping("/orders")
    @PreAuthorize("hasAnyRole('employee','finance')")
    public ResponseEntity<?> getOrders(){
        return new ResponseEntity<>("orders is accessible by all employees", HttpStatus.OK);
    }

    @GetMapping("/payments")
    @PreAuthorize("hasRole('finance')")
    public ResponseEntity<?> getPayments(){
        return new ResponseEntity<>("payments is accessible only by employees of the finance department", HttpStatus.OK);

    }


}
