package net.bodz.bas.log;

import net.bodz.bas.log.objects.ILogEntry;
import net.bodz.bas.sio.NullIndentedCharOut;

public class NullLogSink
        extends NullIndentedCharOut
        implements ILogSink {

    @Override
    public int getVerboseLevel() {
        return 0;
    }

    @Override
    public void setVerboseLevel(int level) {
    }

    @Override
    public void p(ILogEntry entry) {
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
    public void p_(Object obj) {
    }

    @Override
    public void p_(Object... concatObjs) {
    }

    @Override
    public void p_(Throwable exception) {
    }

    @Override
    public void p_(Throwable exception, Object message) {
    }

    @Override
    public void p_(Throwable exception, Object... messagePieces) {
    }

    @Override
    public void f(String format, Object... args) {
    }

    @Override
    public void f(Throwable exception, String format, Object... args) {
    }

    private static final NullLogSink instance = new NullLogSink();

    public static NullLogSink getInstance() {
        return instance;
    }

}
