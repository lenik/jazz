package user.logs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class HelloWithApacheLogger {

    Log logger;

    public HelloWithApacheLogger() {
        logger = LogFactory.getLog(HelloWithApacheLogger.class);
    }

    public void hello() {
        logger.info("Hello from " + getClass());
    }

    @Test
    public void test() {
        hello();
    }

}
