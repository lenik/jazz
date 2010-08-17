package net.bodz.bas.log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.hint.ThreadUnsafe;
import net.bodz.bas.log.objects.ArrayJoinMessage;
import net.bodz.bas.log.objects.IMessage;
import net.bodz.bas.log.objects.LogEntry;
import net.bodz.bas.log.objects.StringFormatMessage;
import net.bodz.bas.sio.AbstractIndentedCharOut;

@ThreadUnsafe
public abstract class AbstractLogSink
        extends AbstractIndentedCharOut
        implements ILogSink {

    // private Object source;

    private int verboseLevel;
    protected boolean visible;

    private List<Object> prependMessageBuffer;

    public AbstractLogSink() {
        prependMessageBuffer = new ArrayList<Object>();
        setVerboseLevel(LEVEL_DEFAULT);
    }

    @Override
    public int getVerboseLevel() {
        return verboseLevel;
    }

    @Override
    public void setVerboseLevel(int level) {
        this.verboseLevel = level;
        int configuredLevel = getConfiguredVerboseLevel();
        visible = verboseLevel >= configuredLevel;
    }

    protected abstract int getConfiguredVerboseLevel();

    protected void _drop(Object message) {
        // Object source = Caller.getCaller
        LogEntry entry = new LogEntry(null, message, null);
        drop(entry);
    }

    protected void _drop(Object message, Throwable exception) {
        // Object source = Caller.getCaller
        LogEntry entry = new LogEntry(null, message, exception);
        drop(entry);
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
    public void p(Object message) {
        if (visible)
            _drop(endup(message));
        else
            prependMessageBuffer.clear();
    }

    @Override
    public void p(Object... messagePieces) {
        if (visible)
            _drop(endup(messagePieces));
        else
            prependMessageBuffer.clear();
    }

    @Override
    public void p(Throwable exception, Object message) {
        if (visible)
            _drop(endup(message), exception);
        else
            prependMessageBuffer.clear();
    }

    @Override
    public void p(Throwable exception, Object... messagePieces) {
        if (visible)
            _drop(endup(messagePieces), exception);
        else
            prependMessageBuffer.clear();
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

        if (visible) {
            Object message = endup();
            assert message != null;
            _drop(message);
        } else
            prependMessageBuffer.clear();
    }

    @Override
    public void _done(Throwable exception) {
        if (visible)
            _drop(endup(), exception);
        else
            prependMessageBuffer.clear();
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
