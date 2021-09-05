package net.bodz.bas.log.log4j;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class Tester {

    static final Logger logger = LoggerFactory.getLogger(Tester.class);

    public static void main(String[] args) {
        logger.info("Info");
        logger.debug("debug");
        logger.error("error");
    }

}
