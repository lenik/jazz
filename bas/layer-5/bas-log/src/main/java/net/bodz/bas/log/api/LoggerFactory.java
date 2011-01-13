package net.bodz.bas.log.api;

import net.bodz.bas.log.impl.Log4jLogComposite;

public class LoggerFactory {

    public static Logger getLogger(Class<?> clazz) {
        return Log4jLogComposite.getInstance(clazz);
    }

    public static Logger getLogger(String name) {
        return Log4jLogComposite.getInstance(name);
    }

}
