package net.bodz.bas.log.logImpls;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class ClDog {

    Log logger;

    public ClDog() {
        logger = LogFactory.getLog(ClDog.class);
    }

    public void hello() {
        logger.info("Hello from " + getClass());
    }

    @Test
    public void test() {
        hello();
    }

}
