package net.bodz.bas.log;

import net.bodz.bas.ctx.scope.ScopedRef;
import net.bodz.bas.log.impl.ConsoleLogger;

public class LoggerScr
        extends ScopedRef<Logger> {

    public LoggerScr() {
        super(Logger.class);
    }

    @Override
    public Logger getDefaultValue() {
        return ConsoleLogger.getInstance();
    }

}
