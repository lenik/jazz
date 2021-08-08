package net.bodz.bas.log;

import java.util.ServiceLoader;

import org.apache.log4j.LogManager;
import org.apache.log4j.spi.LoggerRepository;
import org.slf4j.spi.LocationAwareLogger;

import net.bodz.bas.log.impl.Log4jLogger;
import net.bodz.bas.log.impl.Slf4jLogger;
import net.bodz.bas.log.impl.Slf4jSimpleLogger;

public class LoggerFactory {

    static {
        for (ILoggingSystemConfigurer listener : ServiceLoader.load(ILoggingSystemConfigurer.class))
            listener.initLoggingSystem();
    }

    public static Logger getRootLogger() {
        return getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
    }

    public static Logger getLogger(Class<?> clazz) {
        org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(clazz);
        return convert(logger);
    }

    public static Logger getLogger(String name) {
        org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(name);
        return convert(logger);
    }

    /**
     * for log4j
     */
    public static Logger findClosestRepoLogger(String name) {
        LoggerRepository repo;
        try {
            repo = LogManager.getLoggerRepository();
        } catch (NoClassDefFoundError se) {
            return null;
        }

        org.apache.log4j.Logger log4j = null;
        while (name != null) {
            log4j = repo.getLogger(name);
            if (log4j.getLevel() != null)
                break;
            int lastDot = name.lastIndexOf('.');
            if (lastDot != -1)
                name = name.substring(0, lastDot);
            else
                name = null;
        }

        if (log4j == null)
            log4j = repo.getRootLogger();
        return new Log4jLogger(log4j);
    }

    static Logger convert(org.slf4j.Logger slf4j) {
        if (slf4j instanceof LocationAwareLogger) {
            LocationAwareLogger lal = (LocationAwareLogger) slf4j;
            return new Slf4jLogger(lal);
        } else {
            return new Slf4jSimpleLogger(slf4j);
        }
    }

}
