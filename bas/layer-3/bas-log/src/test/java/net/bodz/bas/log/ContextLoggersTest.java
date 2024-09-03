package net.bodz.bas.log;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ContextLoggersTest {

    static final Logger _logger = LoggerFactory.getLogger(ContextLoggersTest.class);

    ILogger logger = ContextLoggers.compose(ContextLoggersTest.class, _logger);

    void barkWithLogs() {
        logger.info("eat something before bark()");
        logger.debug("  apple");
        logger.debug("  milk");

        logger.info("BaRk!!");

        logger.warn("erhh, hungry again");
        logger.fatal("nothing to eat!");
    }

    @Test
    public void test() {
        BufferedLogger buf = new BufferedLogger();
        String prefix = "C> ";
        TransformedLogger prefixed = new TransformedLogger(buf, s -> prefix + s);

        ContextLoggers.run(prefixed, () -> barkWithLogs());
        assertEquals(4, buf.records.size());

        barkWithLogs();
        assertEquals(4, buf.records.size());
    }

}
