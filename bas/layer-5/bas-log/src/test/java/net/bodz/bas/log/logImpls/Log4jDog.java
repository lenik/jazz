package net.bodz.bas.log.logImpls;

import org.apache.log4j.Logger;
import org.junit.Test;

public class Log4jDog {

    Logger logger = Logger.getLogger(Log4jDog.class);

    public void hello() {
        logger.info("Hello from " + getClass());
    }

    @Test
    public void test1()
            throws Exception {
        hello();
    }

}
