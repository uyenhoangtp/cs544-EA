package bank.logging;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Logger implements ILogger{

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);

	@Override
	public void log(String logstring) {
//		java.util.logging.Logger.getLogger("BankLogger").info(logstring);
		logger.debug(logstring);
	}

}
