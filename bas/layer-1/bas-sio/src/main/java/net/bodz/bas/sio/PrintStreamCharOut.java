package net.bodz.bas.sio;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

public class PrintStreamCharOut
        extends CharOut {

    private final PrintStream ps;

    /**
     * @throws NullPointerException
     *             If <code>ps</code> is <code>null</code>.
     */
    public PrintStreamCharOut(PrintStream ps) {
        if (ps == null)
            throw new NullPointerException("ps");
        this.ps = ps;
    }

    @Override
    public void write(int c)
            throws IOException {
        ps.print((char) c);
    }

    @Override
    public void write(char[] chars, int off, int len)
            throws IOException {
        ps.print(Arrays.copyOfRange(chars, off, off + len));
    }

    @Override
    public void write(String string, int off, int len)
            throws IOException {
        ps.print(string.substring(off, off + len));
    }

    @Override
    public void println() {
        ps.println();
    }

    @Override
    public void print(Object obj) {
        ps.print(obj);
    }

    @Override
    public void print(String s) {
        ps.print(s);
    }

    @Override
    public void flush()
            throws IOException {
        ps.flush();
    }

}
