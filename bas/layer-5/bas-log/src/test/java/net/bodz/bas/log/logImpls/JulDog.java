package net.bodz.bas.log.logImpls;

import java.util.logging.Logger;

import org.junit.Test;

public class JulDog {

    Logger logger = Logger.getLogger(JulDog.class.getName());

    public void hello() {
        logger.info("Hello from " + getClass().getName());
    }

    @Test
    public void test() {
        hello();
        hello();
    }

}
