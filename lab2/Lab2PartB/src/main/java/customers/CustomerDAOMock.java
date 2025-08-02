package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class CustomerDAOMock implements ICustomerDAO{
	private ILogger logger;

	@Autowired
	public CustomerDAOMock(ILogger logger) {
		this.logger = logger;
	}

	@Override
	public void save(Customer customer) {
		System.out.println("CustomerDAO Mock: saving customer "+customer.getName());
		logger.log("Customer is saved in the DB: "+ customer.getName() );
	}
}
