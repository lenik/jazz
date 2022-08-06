package net.bodz.bas.log;

import java.util.ServiceLoader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.spi.LoggerContext;
import org.apache.logging.log4j.spi.LoggerRegistry;
import org.slf4j.spi.LocationAwareLogger;

import net.bodz.bas.log.impl.Log4jLogger;
import net.bodz.bas.log.impl.Slf4jLogger;
import net.bodz.bas.log.impl.Slf4jSimpleLogger;

public class LoggerFactory {

    static boolean inited;
    static {
        init();
    }

    public static void init() {
        if (inited)
            return;
        for (ILoggingSystemConfigurer listener : ServiceLoader.load(ILoggingSystemConfigurer.class))
            listener.initLoggingSystem();
        inited = true;
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
        LoggerContext context;
        LoggerRegistry<?> registry;
        try {
            context = LogManager.getContext();
            registry = context.getLoggerRegistry();
        } catch (NoClassDefFoundError se) {
            return null;
        }

        org.apache.logging.log4j.Logger log4j = null;
        while (name != null) {
            log4j = registry.getLogger(name);
            if (log4j.getLevel() != null)
                break;
            int lastDot = name.lastIndexOf('.');
            if (lastDot != -1)
                name = name.substring(0, lastDot);
            else
                name = null;
        }

        if (log4j == null)
            log4j = null; // TODO registry.getRootLogger();
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
