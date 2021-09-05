package net.bodz.bas.log.log4j;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggerRepository;

public class DefaultLog4jConfigurer
        implements
            ILog4jConfigurer {

    @Override
    public int getPriority() {
        return -1000;
    }

    @Override
    public void initLog4j(LoggerRepository hierachy) {
        Logger rootLogger = hierachy.getRootLogger();
        rootLogger.setLevel(Level.WARN);

        PatternLayout layout = new PatternLayout();
        layout.setConversionPattern("%d %p [%X{contexts}] (%F:%L) %m%n");
        ConsoleAppender stdout = new ConsoleAppender(layout);
        rootLogger.addAppender(stdout);

        Logger logger;
        logger = hierachy.getLogger("net.bodz");
        logger.setLevel(Level.INFO);

        logger = hierachy.getLogger("user");
        logger.setLevel(Level.DEBUG);
    }

}
