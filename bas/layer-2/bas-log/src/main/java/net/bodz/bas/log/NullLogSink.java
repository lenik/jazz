package net.bodz.bas.log;

public class NullLogSink
        implements ILogSink {

    @Override
    public int getVerboseLevel() {
        return 0;
    }

    @Override
    public void setVerboseLevel(int level) {
    }

    @Override
    public void p(Object obj) {
    }

    @Override
    public void p(Object... concatObjs) {
    }

    @Override
    public void p(Object obj, Throwable t) {
    }

    @Override
    public void p_(Object... concatObjs) {
    }

    @Override
    public void p_(Object obj) {
    }

    @Override
    public void p_() {
    }

    @Override
    public void f(String format, Object... args) {
    }

    static final NullLogSink instance = new NullLogSink();

    public static NullLogSink getInstance() {
        return instance;
    }

}
