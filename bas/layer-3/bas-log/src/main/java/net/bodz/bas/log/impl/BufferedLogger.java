package net.bodz.bas.log.impl;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.jvm.stack.StackTrace;
import net.bodz.bas.log.AbstractLogSink;
import net.bodz.bas.log.AbstractLogger;
import net.bodz.bas.log.ILogSink;
import net.bodz.bas.log.LogLevel;
import net.bodz.bas.sio.BCharOut;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sio.PrintStreamPrintOut;

public class BufferedLogger
        extends AbstractLogger {

    String prefix;
    List<LogRecord> records = new ArrayList<LogRecord>();

    public BufferedLogger() {
        this("");
    }

    public BufferedLogger(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        if (prefix == null)
            throw new NullPointerException("prefix");
        this.prefix = prefix;
    }

    public List<LogRecord> getRecords() {
        return records;
    }

    @Override
    public String toString() {
        BCharOut buf = new BCharOut();
        dump(buf, buf);
        return buf.toString();
    }

    public void dump() {
        dump(System.out, System.err);
        records.clear();
    }

    public void dump(PrintStream info, PrintStream err) {
        PrintStreamPrintOut infoOut = new PrintStreamPrintOut(info);
        PrintStreamPrintOut errOut;

        if (info == err)
            errOut = infoOut;
        else
            errOut = new PrintStreamPrintOut(err);

        dump(infoOut, errOut);
    }

    public void dump(IPrintOut info, IPrintOut err) {
        synchronized (records) {
            for (LogRecord record : records) {
                int priority = record.level.getPriority() + record.delta;
                IPrintOut out = priority < 0 ? err : info;

                out.print(prefix);

                String levelName = record.level.getDisplayName();
                out.print("[" + levelName + "] ");

                out.println(record.message);

                if (record.exception != null) {
                    String stackTrace = StackTrace.get(record.exception);
                    err.print(stackTrace);
                }
            }
        }
    }

    @Override
    public ILogSink get(LogLevel level, int delta) {
        if (level.getGroup() != LogLevel.logGroup)
            return super.get(level, delta);
        else
            return new Sink(level, delta);
    }

    class Sink
            extends AbstractLogSink {

        LogLevel level;
        int delta;

        public Sink(LogLevel level, int delta) {
            if (level == null)
                throw new NullPointerException("level");
            this.level = level;
            this.delta = delta;
        }

        @Override
        public void logMessage(Object message) {
            LogRecord item = new LogRecord(level, delta, message);
            records.add(item);
        }

        @Override
        public void logException(Object message, Throwable exception) {
            LogRecord item = new LogRecord(level, delta, message, exception);
            records.add(item);
        }

    }

    @Override
    public boolean _fatal(int delta, Throwable e, Object message) {
        LogRecord item = new LogRecord(LogLevel.FATAL, delta, message, e);
        records.add(item);
        return false;
    }

    @Override
    public boolean _error(int delta, Throwable e, Object message) {
        LogRecord item = new LogRecord(LogLevel.ERROR, delta, message, e);
        records.add(item);
        return false;
    }

    @Override
    public void _warn(int delta, Throwable e, Object message) {
        LogRecord item = new LogRecord(LogLevel.WARN, delta, message, e);
        records.add(item);
    }

    @Override
    public void _info(int delta, Throwable e, Object message) {
        LogRecord item = new LogRecord(LogLevel.INFO, delta, message, e);
        records.add(item);
    }

    @Override
    public void _log(int delta, Throwable e, Object message) {
        LogRecord item = new LogRecord(LogLevel.LOG, delta, message, e);
        records.add(item);
    }

    @Override
    public void _debug(int delta, Throwable e, Object message) {
        LogRecord item = new LogRecord(LogLevel.DEBUG, delta, message, e);
        records.add(item);
    }

    @Override
    public void _trace(int delta, Throwable e, Object message) {
        LogRecord item = new LogRecord(LogLevel.TRACE, delta, message, e);
        records.add(item);
    }

}

class LogRecord {

    LogLevel level;
    int delta;
    Object message;
    Throwable exception;

    public LogRecord(LogLevel level, int delta, Object message) {
        this.level = level;
        this.delta = delta;
        this.message = message;
    }

    public LogRecord(LogLevel level, int delta, Object message, Throwable exception) {
        this.level = level;
        this.delta = delta;
        this.message = message;
        this.exception = exception;
    }

}