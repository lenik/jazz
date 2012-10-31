package user.logs;

import org.apache.log4j.Logger;
import org.junit.Test;

public class HelloWithLog4jLogger {

    Logger logger = Logger.getLogger(HelloWithLog4jLogger.class);

    public void hello() {
        logger.info("Hello from " + getClass());
    }

    @Test
    public void test1()
            throws Exception {
        hello();
    }

}
