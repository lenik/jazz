package net.bodz.bas.io.term;

public abstract class BufferedTerminal
        extends AbstractTerminal {

    boolean started = false;
    StringBuffer buf;

    public BufferedTerminal() {
        buf = new StringBuffer();
    }

    void restart() {
        buf.delete(0, buf.length());
        started = true;
    }

    @Override
    public void p_(String s) {
        if (!started)
            restart();
        buf.append(s);
        if (s.endsWith("\n"))
            p();
    }

    @Override
    public void p() {
        String s = started ? buf.toString() : "";
        started = false;
        _p(s);
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
