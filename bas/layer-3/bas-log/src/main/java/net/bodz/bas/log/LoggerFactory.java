package net.bodz.bas.log;

import java.util.ServiceLoader;

import net.bodz.bas.log.impl.Log4jLogger;

public class LoggerFactory {

    static {
        for (ILoggingSystemConfigurer listener : ServiceLoader.load(ILoggingSystemConfigurer.class))
            listener.initLoggingSystem();
    }

    public static Logger getLogger(Class<?> clazz) {
        return Log4jLogger.getInstance(clazz);
    }

    public static Logger getLogger(String name) {
        return Log4jLogger.getInstance(name);
    }

}
