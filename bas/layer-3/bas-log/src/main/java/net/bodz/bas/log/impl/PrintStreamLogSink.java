package net.bodz.bas.log.impl;

import java.io.PrintStream;

import net.bodz.bas.log.AbstractLogSink;
import net.bodz.bas.log.ILogEntry;

public class PrintStreamLogSink
        extends AbstractLogSink {

    private final PrintStream stream;

    public PrintStreamLogSink(PrintStream printStream) {
        if (printStream == null)
            throw new NullPointerException("printStream");
        this.stream = printStream;
    }

    @Override
    public void log(ILogEntry entry) {
        stream.println(entry);
    }

    @Override
    public void logMessage(Object message) {
        stream.println(message);
    }

    @Override
    public void logException(Object message, Throwable exception) {
        stream.println(message);
        if (exception != null)
            exception.printStackTrace(stream);
    }

    public static PrintStreamLogSink stdout = new PrintStreamLogSink(System.out);
    public static PrintStreamLogSink stderr = new PrintStreamLogSink(System.err);

}
