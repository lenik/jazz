package net.bodz.bas.log;

import net.bodz.bas.log.log4j.Log4jLogger;

public class LoggerFactory {

    public static Logger getLogger(Class<?> clazz) {
        return Log4jLogger.getInstance(clazz);
    }

    public static Logger getLogger(String name) {
        return Log4jLogger.getInstance(name);
    }

}
