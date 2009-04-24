package net.bodz.bas.io.term;

public abstract class BufferedTerminal extends _Terminal {

    boolean      started = false;
    StringBuffer buf;

    public BufferedTerminal() {
        buf = new StringBuffer();
    }

    void restart() {
        buf.delete(0, buf.length());
        started = true;
    }

    @Override
    public void n(String s) {
        if (!started)
            restart();
        buf.append(s);
        if (s.endsWith("\n")) //$NON-NLS-1$
            p();
    }

    @Override
    public void p() {
        String s = started ? buf.toString() : ""; //$NON-NLS-1$
        started = false;
        _p(s);
    }

    @Override
    public void t(String s) {
        if (started)
            p();
        // state: shall erase?
        eraseLast();
        _t(s);
    }

    @Override
    public void p(String s) {
        if (started) {
            s = buf.toString() + s;
            started = false;
        }
        _p(s);
    }

    protected abstract void _p(String s);

    protected void _t(String s) {
        _p(s);
    }

    protected void eraseLast() {
    }

}
