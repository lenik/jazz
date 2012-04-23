package net.bodz.bas.log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.log.message.ArrayJoinMessage;
import net.bodz.bas.log.message.IMessage;
import net.bodz.bas.log.message.StringFormatMessage;
import net.bodz.bas.meta.codereview.ThreadUnsafe;
import net.bodz.bas.sio.AbstractStackedOut;

@ThreadUnsafe
public abstract class AbstractLogSink
        extends AbstractStackedOut
        implements ILogSink {

    // private Object source;

    private List<Object> prependMessageBuffer;

    public AbstractLogSink() {
        prependMessageBuffer = new ArrayList<Object>();
    }

    @Override
    public void log(ILogEntry entry) {
        Object message = entry.getMessage();
        Throwable exception = entry.getException();
        if (exception == null)
            logMessage(message);
        else
            logException(message, exception);
    }

    private Object endup() {
        switch (prependMessageBuffer.size()) {
        case 0:
            return null;
        case 1:
            Object simpleMessage = prependMessageBuffer.get(0);
            prependMessageBuffer.clear();
            return simpleMessage;
        default:
            Object[] all = prependMessageBuffer.toArray();
            prependMessageBuffer.clear();
            return new ArrayJoinMessage(all);
        }
    }

    private Object endup(Object message) {
        if (!prependMessageBuffer.isEmpty()) {
            prependMessageBuffer.add(message);
            Object[] all = prependMessageBuffer.toArray();
            prependMessageBuffer.clear();
            message = new ArrayJoinMessage(all);
        }
        return message;
    }

    private Object endup(Object... messagePieces) {
        IMessage joined;
        if (!prependMessageBuffer.isEmpty()) {
            int pre = prependMessageBuffer.size();
            Object[] all = new Object[pre + messagePieces.length];
            prependMessageBuffer.toArray(all);
            System.arraycopy(messagePieces, 0, all, pre, messagePieces.length);
            joined = new ArrayJoinMessage(all);
        } else
            joined = new ArrayJoinMessage(messagePieces);
        return joined;
    }

    @Override
    public void p(String message) {
        logMessage(endup(message));
    }

    @Override
    public void p(Object message) {
        logMessage(endup(message));
    }

    @Override
    public void p(Object... messagePieces) {
        logMessage(endup(messagePieces));
    }

    @Override
    public void p(Throwable exception, Object message) {
        logException(endup(message), exception);
    }

    @Override
    public void p(Throwable exception, Object... messagePieces) {
        logException(endup(messagePieces), exception);
    }

    @Override
    public void f(String format, Object... args) {
        IMessage message = new StringFormatMessage(format, args);
        p(message);
    }

    @Override
    public void f(Throwable exception, String format, Object... args) {
        IMessage message = new StringFormatMessage(format, args);
        p(exception, message);
    }

    @Override
    public void _(Object message) {
        prependMessageBuffer.add(message);
    }

    @Override
    public void _(Object... messagePieces) {
        if (messagePieces == null)
            throw new NullPointerException("messagePieces");
        for (int i = 0; i < messagePieces.length; i++)
            prependMessageBuffer.add(messagePieces[i]);
    }

    @Override
    public void _done() {
        if (prependMessageBuffer.isEmpty())
            return;

        Object message = endup();
        assert message != null;
        logMessage(message);
    }

    @Override
    public void _done(Throwable exception) {
        logException(endup(), exception);
    }

    @Override
    protected void _flush(boolean strict) {
        _done();
    }

    // CharOut will filterred by the LogSink.

    @Override
    public void write(String s)
            throws IOException {
        int start = 0;
        int newl;
        while ((newl = s.indexOf('\n', start)) != -1) {
            String line = s.substring(start, newl);
            start = newl + 1;
            p(line);
        }
        s = s.substring(start);
        _(s);
    }

    @Override
    public void write(char[] chars, int off, int len)
            throws IOException {
        _(new String(chars, off, len));
    }

    @Override
    public void write(int ch)
            throws IOException {
        if (ch == '\n')
            _done();
        else
            _(ch);
    }

}
