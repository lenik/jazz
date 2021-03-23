package net.bodz.bas.log;

import java.util.ServiceLoader;

import org.slf4j.spi.LocationAwareLogger;

import net.bodz.bas.log.impl.Slf4jLogger;
import net.bodz.bas.log.impl.Slf4jSimpleLogger;

public class LoggerFactory {

    static {
        for (ILoggingSystemConfigurer listener : ServiceLoader.load(ILoggingSystemConfigurer.class))
            listener.initLoggingSystem();
    }

    public static Logger getLogger(Class<?> clazz) {
        org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(clazz);
        return convert(logger);
    }

    public static Logger getLogger(String name) {
        org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(name);
        return convert(logger);
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
