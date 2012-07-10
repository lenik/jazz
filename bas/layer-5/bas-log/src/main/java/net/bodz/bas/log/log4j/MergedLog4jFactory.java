package net.bodz.bas.log.log4j;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MergedLog4jFactory {

    static {
        try {
            Log4jMerger.load();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * TODO normalize the name.
     */
    public static Logger getLogger(String name) {
        Logger logger = LogManager.getLogger(name);
        return logger;
    }

    public static Logger getLogger(Class<?> clazz) {
        Logger logger = LogManager.getLogger(clazz);
        return logger;
    }

}
