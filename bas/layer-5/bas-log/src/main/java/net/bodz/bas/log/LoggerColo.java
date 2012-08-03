package net.bodz.bas.log;

import net.bodz.bas.context.ContextLocal;
import net.bodz.bas.log.impl.ConsoleLogger;

public class LoggerColo
        extends ContextLocal<Logger> {

    @Override
    public Logger getRoot() {
        return ConsoleLogger.getInstance();
    }

    static final LoggerColo instance = new LoggerColo();

    public static LoggerColo getInstance() {
        return instance;
    }

}
