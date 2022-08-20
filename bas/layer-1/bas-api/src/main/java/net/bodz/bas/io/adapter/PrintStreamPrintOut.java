package net.bodz.bas.io.adapter;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

import net.bodz.bas.io.AbstractPrintOut;

public class PrintStreamPrintOut
        extends AbstractPrintOut {

    private final PrintStream ps;

    /**
     * @throws NullPointerException
     *             If <code>ps</code> is <code>null</code>.
     */
    public PrintStreamPrintOut(PrintStream ps) {
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
    public void print(String s) {
        ps.print(s);
    }

    @Override
    public void _flushX()
            throws IOException {
        ps.flush();
    }

    @Override
    public void _closeX()
            throws IOException {
        ps.close();
    }

}
