package net.bodz.bas.log;

import java.util.ServiceLoader;

import net.bodz.bas.log.impl.Slf4jLogger;

public class LoggerFactory {

    static {
        for (ILoggingSystemConfigurer listener : ServiceLoader.load(ILoggingSystemConfigurer.class))
            listener.initLoggingSystem();
    }

    public static Logger getLogger(Class<?> clazz) {
        return Slf4jLogger.getInstance(clazz);
    }

    public static Logger getLogger(String name) {
        return Slf4jLogger.getInstance(name);
    }

}
