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
        if (s.endsWith("\n"))
            p();
    }

    @Override
    public void p() {
        String s = started ? buf.toString() : "";
        started = false;
        p(s);
    }

    @Override
    public void t(String s) {
        if (started)
            p();
        // state: shall erase?
        eraseLast();
        p(s);
    }

    @Override
    public abstract void p(String s);

    protected void eraseLast() {
    }

}
