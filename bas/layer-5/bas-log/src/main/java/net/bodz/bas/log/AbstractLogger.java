package net.bodz.bas.log;

import net.bodz.bas.log.api.Logger;

public abstract class AbstractLogger
        extends AbstractLogComposite
        implements Logger {

    // Log4j style shortcuts

    @Override
    public void fatal(Object message) {
        getErrorSink().p(message);
    }

    @Override
    public void fatal(Object message, Throwable t) {
        getErrorSink().p(t, message);
    }

    @Override
    public void error(Object message) {
        getErrorSink().p(message);
    }

    @Override
    public void error(Object message, Throwable t) {
        getErrorSink().p(t, message);
    }

    @Override
    public void warn(Object message) {
        getWarnSink().p(message);
    }

    @Override
    public void warn(Object message, Throwable t) {
        getWarnSink().p(t, message);
    }

    @Override
    public void info(Object message) {
        getInfoSink().p(message);
    }

    @Override
    public void info(Object message, Throwable t) {
        getInfoSink().p(t, message);
    }

    @Override
    public void debug(Object message) {
        getDebugSink().p(message);
    }

    @Override
    public void debug(Object message, Throwable t) {
        getDebugSink().p(t, message);
    }

    // Shortcuts

    @Override
    public void fatal(Object... messagePieces) {
        getFatalSink().p(messagePieces);
    }

    @Override
    public void fatal(Throwable e, Object... messagePieces) {
        getFatalSink().p(e, messagePieces);
    }

    @Override
    public void error(Object... messagePieces) {
        getErrorSink().p(messagePieces);
    }

    @Override
    public void error(Throwable e, Object... messagePieces) {
        getErrorSink().p(e, messagePieces);
    }

    @Override
    public void warn(Object... messagePieces) {
        getWarnSink().p(messagePieces);
    }

    @Override
    public void warn(Throwable e, Object... messagePieces) {
        getWarnSink().p(e, messagePieces);
    }

    @Override
    public void info(Object... messagePieces) {
        getInfoSink().p(messagePieces);
    }

    @Override
    public void info(Throwable e, Object... messagePieces) {
        getInfoSink().p(e, messagePieces);
    }

    @Override
    public void debug(Object... messagePieces) {
        getDebugSink().p(messagePieces);
    }

    @Override
    public void debug(Throwable e, Object... messagePieces) {
        getDebugSink().p(e, messagePieces);
    }

}
