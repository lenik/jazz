package net.bodz.bas.log.impl;

import org.junit.Assert;
import org.junit.Test;

public class BufferedLoggerTest
        extends Assert {

    @Test
    public void testLogMessage() {
        BufferedLogger logger = new BufferedLogger();
        logger.info("Info");
        logger.debug("Debug");

        // logger.dump(System.out, System.err);
        String logs = logger.toString();
        assertEquals("[info] Info\n[debug] Debug\n", logs);
    }

    @Test
    public void testLogException() {
        BufferedLogger logger = new BufferedLogger();
        logger.error("Error", new Exception());

        // logger.dump(System.out, System.err);
        String logs = logger.toString();
        assertTrue(logs.startsWith("[error] Error\njava.lang.Exception"));
    }

}
