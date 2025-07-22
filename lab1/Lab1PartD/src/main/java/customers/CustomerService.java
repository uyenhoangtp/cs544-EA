package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {
	private ICustomerDAO customerDAO;
	private IEmailSender emailSender;

	public void addCustomer(String name, String email, String street,
			String city, String zip) {
		Customer customer = new Customer(name, email);
		Address address = new Address(street, city, zip);
		customer.setAddress(address);
		customerDAO.save(customer);
		emailSender.sendEmail(email, "Welcome " + name + " as a new customer");
	}

	//setter injection
	@Autowired
	public void setCustomerDAO (ICustomerDAO customerDAO){
		this.customerDAO = customerDAO;
	}

	@Autowired
	public void setEmailSender (IEmailSender emailSender) {
		this.emailSender = emailSender;
	}
}
