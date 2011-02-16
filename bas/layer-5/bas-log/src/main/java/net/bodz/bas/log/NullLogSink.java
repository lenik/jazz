package net.bodz.bas.log;

import net.bodz.bas.sio.NullIndentedCharOut;

public class NullLogSink
        extends NullIndentedCharOut
        implements ILogSink {

    @Override
    public void log(ILogEntry entry) {
    }

    @Override
    public void logMessage(Object message) {
    }

    @Override
    public void logException(Object message, Throwable exception) {
    }

    @Override
    public void p(String message) {
    }

    @Override
    public void p(Object obj) {
    }

    @Override
    public void p(Object... concatObjs) {
    }

    @Override
    public void p(Throwable t, Object obj) {
    }

    @Override
    public void p(Throwable exception, Object... messagePieces) {
    }

    @Override
    public void f(String format, Object... args) {
    }

    @Override
    public void f(Throwable exception, String format, Object... args) {
    }

    @Override
    public void _(Object message) {
    }

    @Override
    public void _(Object... messagePieces) {
    }

    @Override
    public void _done() {
    }

    @Override
    public void _done(Throwable exception) {
    }

    private static final NullLogSink instance = new NullLogSink();

    public static NullLogSink getInstance() {
        return instance;
    }

}
