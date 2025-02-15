package utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class MyRetryAnalyzer implements IRetryAnalyzer {
	
	private static final Logger logger = LoggerFactory.getLogger(MyRetryAnalyzer.class);
    private int counter = 0;
    private int retryLimit = 3;

    @Override
    public boolean retry(ITestResult result) {
        if (counter < retryLimit) {
            counter++;
            logger.info("Retrying test: " + result.getName() + " for the " + counter + " time(s)");
            return true;
        } else {
            logger.info("Test " + result.getName() + " failed after " + retryLimit + " retry attempts.");
        }
        return false;
    }
}
