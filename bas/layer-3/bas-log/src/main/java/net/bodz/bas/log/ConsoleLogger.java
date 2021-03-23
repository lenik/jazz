package net.bodz.bas.log;

import net.bodz.bas.log.impl.PrintStreamLogSink;

public class ConsoleLogger
        extends SinkBasedLogger {

    static ConsoleLogger instance = new ConsoleLogger();

    @Override
    public ILogSink get(LogLevel level, int delta) {
        if (level.getGroup() != LogLevel.logGroup)
            return super.get(level, delta);

        int priority = level.getPriority() + delta;
        if (priority < 0)
            return PrintStreamLogSink.stderr;
        else
            return PrintStreamLogSink.stdout;
    }

    public static ConsoleLogger getInstance() {
        return instance;
    }

}
