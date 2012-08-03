package user.logs;

import java.util.logging.Logger;

import org.junit.Test;

public class HelloWithJavaUtilLogger {

    Logger logger = Logger.getLogger(HelloWithJavaUtilLogger.class.getName());

    public void hello() {
        logger.info("Hello from " + getClass().getName());
    }

    @Test
    public void test() {
        hello();
        hello();
    }

}
