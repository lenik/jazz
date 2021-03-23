package net.bodz.bas.log.impl;

import net.bodz.bas.log.ConsoleLogger;

public class ConsoleLoggerTest {

    public static void main(String[] args) {
        ConsoleLogger logger = ConsoleLogger.getInstance();
        logger.warn("warn");
        logger.mesg("mesg");
        logger.info("info");
        logger.error("error");
    }

}
