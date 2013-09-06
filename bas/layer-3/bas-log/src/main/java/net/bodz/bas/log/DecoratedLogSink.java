package net.bodz.bas.log;

import net.bodz.bas.io.DecoratedTreeOut;

public class DecoratedLogSink
        extends DecoratedTreeOut
        implements ILogSink {

    private static final long serialVersionUID = 1L;

    public DecoratedLogSink(ILogSink _orig) {
        super(_orig);
    }

    @Override
    public ILogSink getWrapped() {
        return (ILogSink) _orig;
    }

    @Override
    public void log(ILogEntry entry) {
        getWrapped().log(entry);
    }

    @Override
    public void logMessage(Object message) {
        getWrapped().logMessage(message);
    }

    @Override
    public void logException(Object message, Throwable exception) {
        getWrapped().logException(message, exception);
    }

    @Override
    public void p(String message) {
        getWrapped().p(message);
    }

    @Override
    public void p(Object message) {
        getWrapped().p(message);
    }

    @Override
    public void p(Object... messagePieces) {
        getWrapped().p(messagePieces);
    }

    @Override
    public void p(Throwable exception, Object message) {
        getWrapped().p(exception, message);
    }

    @Override
    public void p(Throwable exception, Object... messagePieces) {
        getWrapped().p(exception, messagePieces);
    }

    @Override
    public void f(String format, Object... args) {
        getWrapped().f(format, args);
    }

    @Override
    public void f(Throwable exception, String format, Object... args) {
        getWrapped().f(exception, format, args);
    }

    @Override
    public void _(Object message) {
        getWrapped()._(message);
    }

    @Override
    public void _(Object... messagePieces) {
        getWrapped()._(messagePieces);
    }

    @Override
    public void _done() {
        getWrapped()._done();
    }

    @Override
    public void _done(Throwable exception) {
        getWrapped()._done(exception);
    }

}
