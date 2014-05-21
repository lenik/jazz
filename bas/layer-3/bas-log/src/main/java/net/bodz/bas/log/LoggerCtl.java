package net.bodz.bas.log;

import net.bodz.bas.context.ContextLocal;
import net.bodz.bas.log.impl.ConsoleLogger;

public class LoggerCtl
        extends ContextLocal<Logger> {

    public LoggerCtl() {
        super(Logger.class);
    }

    @Override
    public Logger getDefaultValue() {
        return ConsoleLogger.getInstance();
    }

}
