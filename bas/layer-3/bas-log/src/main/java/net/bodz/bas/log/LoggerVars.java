package net.bodz.bas.log;

import net.bodz.bas.ctx.scope.ScopedRef;

public class LoggerVars
        extends ScopedRef<Logger> {

    public LoggerVars() {
        super(Logger.class);
    }

    @Override
    public Logger getDefaultValue() {
        return ConsoleLogger.getInstance();
    }

}
