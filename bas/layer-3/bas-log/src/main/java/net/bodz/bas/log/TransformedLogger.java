package net.bodz.bas.log;

import java.util.function.Function;

public class TransformedLogger
        extends AbstractLogger {

    ILogger orig;
    Function<String, String> messageFn;

    public TransformedLogger(ILogger orig, Function<String, String> messageFn) {
        if (orig == null)
            throw new NullPointerException("orig");
        if (messageFn == null)
            throw new NullPointerException("messageFn");
        this.orig = orig;
        this.messageFn = messageFn;
    }

    String transformMessage(Object message) {
        if (message == null)
            return null;
        String src = message.toString();
        String dst = messageFn.apply(src);
        return dst;
    }

    @Override
    public void _stderr(int delta, Throwable e, Object message) {
        orig._stderr(delta, e, transformMessage(message));
    }

    @Override
    public void _stdout(int delta, Throwable e, Object message) {
        orig._stdout(delta, e, transformMessage(message));
    }

    @Override
    public boolean _fatal(int delta, Throwable e, Object message) {
        return orig._fatal(delta, e, transformMessage(message));
    }

    @Override
    public boolean _error(int delta, Throwable e, Object message) {
        return orig._error(delta, e, transformMessage(message));
    }

    @Override
    public void _warn(int delta, Throwable e, Object message) {
        orig._warn(delta, e, transformMessage(message));
    }

    @Override
    public void _mesg(int delta, Throwable e, Object message) {
        orig._mesg(delta, e, transformMessage(message));
    }

    @Override
    public void _info(int delta, Throwable e, Object message) {
        orig._info(delta, e, transformMessage(message));
    }

    @Override
    public void _log(int delta, Throwable e, Object message) {
        orig._log(delta, e, transformMessage(message));
    }

    @Override
    public void _debug(int delta, Throwable e, Object message) {
        orig._debug(delta, e, transformMessage(message));
    }

    @Override
    public void _trace(int delta, Throwable e, Object message) {
        orig._trace(delta, e, transformMessage(message));
    }

    @Override
    public void _status(int delta, Throwable e, Object message) {
        orig._status(delta, e, transformMessage(message));
    }

    @Override
    public void _progress(int delta, Throwable e, Object message) {
        orig._progress(delta, e, transformMessage(message));
    }

}
