package user.logs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class HelloWithLog4jLogger {

    static Logger logger = LogManager.getLogger(HelloWithLog4jLogger.class);

    public void hello() {
        logger.info("Hello from " + getClass());
    }

    @Test
    public void test1()
            throws Exception {
        hello();
    }

}
