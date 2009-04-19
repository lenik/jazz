package net.bodz.bas.io.term;

public abstract class BufferedTerminal extends _Terminal {

    boolean      invalid = false;
    StringBuffer buf;

    public BufferedTerminal() {
        buf = new StringBuffer();
    }

    void clear() {
        buf.delete(0, buf.length());
        invalid = false;
    }

    @Override
    public void n(String s) {
        if (invalid)
            clear();
        buf.append(s);
    }

    @Override
    public void p() {
        String s = invalid ? "" : buf.toString();
        invalid = true;
        p(s);
    }

    @Override
    public void t(String s) {
        if (!invalid)
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
