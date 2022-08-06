package net.bodz.bas.log.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Deprecated
public class Log4jMergedFactory {

    static {
        Log4jMerger.load();
    }

    public static Logger getLogger(String name) {
        Logger logger = LogManager.getLogger(name);
        return logger;
    }

}
