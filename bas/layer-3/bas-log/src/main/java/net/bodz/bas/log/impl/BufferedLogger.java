package net.bodz.bas.log.impl;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.adapter.PrintStreamPrintOut;
import net.bodz.bas.jvm.stack.StackTrace;
import net.bodz.bas.log.AbstractLogSink;
import net.bodz.bas.log.ILogSink;
import net.bodz.bas.log.LogLevel;
import net.bodz.bas.log.LogRecord;
import net.bodz.bas.log.SinkBasedLogger;

public class BufferedLogger
        extends SinkBasedLogger {

    static final int defaultMaxRecordCount = 10000;

    String prefix;

    int maxRecordCount;

    // BlockingQueue<E>
    // ConcurrentLinkedQueue<E>
    LinkedList<LogRecord> records;

    public BufferedLogger() {
        this("", defaultMaxRecordCount);
    }

    public BufferedLogger(String prefix) {
        this(prefix, defaultMaxRecordCount);
    }

    public BufferedLogger(String prefix, int maxRecordCount) {
        this.prefix = prefix;
        this.maxRecordCount = maxRecordCount;
        this.records = new LinkedList<LogRecord>();
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

    public int getMaxRecordCount() {
        return maxRecordCount;
    }

    synchronized void addRecord(LogRecord record) {
        if (record.level.compareTo(getLevel()) > 0)
            return;
        while (records.size() >= maxRecordCount)
            records.removeFirst();
        records.addLast(record);
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

    @SuppressWarnings("resource")
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
        for (LogRecord record : records) {
            int priority = record.level.getPriority() + record.delta;
            IPrintOut out = priority < 0 ? err : info;

            out.print(prefix);

            String levelName = record.level.getLabel();
            out.print("[" + levelName + "] ");

            out.println(record.message);

            if (record.exception != null) {
                String stackTrace = StackTrace.get(record.exception);
                err.print(stackTrace);
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
            addRecord(item);
        }

        @Override
        public void logException(Object message, Throwable exception) {
            LogRecord item = new LogRecord(level, delta, message, exception);
            addRecord(item);
        }

    }

    @Override
    public boolean _fatal(int delta, Throwable e, Object message) {
        LogRecord item = new LogRecord(LogLevel.FATAL, delta, message, e);
        addRecord(item);
        return false;
    }

    @Override
    public boolean _error(int delta, Throwable e, Object message) {
        LogRecord item = new LogRecord(LogLevel.ERROR, delta, message, e);
        addRecord(item);
        return false;
    }

    @Override
    public void _warn(int delta, Throwable e, Object message) {
        LogRecord item = new LogRecord(LogLevel.WARN, delta, message, e);
        addRecord(item);
    }

    @Override
    public void _info(int delta, Throwable e, Object message) {
        LogRecord item = new LogRecord(LogLevel.INFO, delta, message, e);
        addRecord(item);
    }

    @Override
    public void _log(int delta, Throwable e, Object message) {
        LogRecord item = new LogRecord(LogLevel.LOG, delta, message, e);
        addRecord(item);
    }

    @Override
    public void _debug(int delta, Throwable e, Object message) {
        LogRecord item = new LogRecord(LogLevel.DEBUG, delta, message, e);
        addRecord(item);
    }

    @Override
    public void _trace(int delta, Throwable e, Object message) {
        LogRecord item = new LogRecord(LogLevel.TRACE, delta, message, e);
        addRecord(item);
    }

}
