package net.bodz.bas.log.impl;

import java.io.PrintStream;

import net.bodz.bas.log.AbstractLogSink;

public class PrintStreamStatusSink
        extends AbstractLogSink {

    private final PrintStream stream;

    public PrintStreamStatusSink(PrintStream printStream) {
        if (printStream == null)
            throw new NullPointerException("printStream");
        this.stream = printStream;
    }

    @Override
    protected void logMessage(Object message) {
        stream.print('\r');
        // output(COLUMNS * ' ');
        stream.print(message);
    }

    @Override
    protected void logException(Object message, Throwable exception) {
        stream.print('\r');
        stream.print(message);
        if (exception != null) {
            stream.println();
            exception.printStackTrace(stream);
        }
    }

}
