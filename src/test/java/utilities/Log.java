package utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log {

	public static Logger logger;

	public static void startTestCase(String TestName) {
		// Initialize Log4j logs
		logger = Logger.getLogger(Log.class.getName());
		PropertyConfigurator.configure("Log4j.properties");

		logger.info("=====================================" + TestName
				+ " TEST START=========================================");

	}

	public static void endTestCase(String TestName) {
		logger.info("=====================================" + TestName
				+ " TEST END=========================================");
	}

	// Need to create below methods, so that they can be called

	public static void info(String message) {

		logger.info(message);

	}

	public static void warn(String message) {

		logger.warn(message);

	}

	public static void error(String message) {

		logger.error(message);

	}

	public static void fatal(String message) {

		logger.fatal(message);

	}

	public static void debug(String message) {

		logger.debug(message);

	}

}
