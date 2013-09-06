package net.bodz.bas.io.adapter;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

import net.bodz.bas.io.AbstractPrintOut;

public abstract class DynamicPrintStreamPrintOut
        extends AbstractPrintOut {

    /**
     * @return non-<code>null</code> {@link PrintStream} instance.
     */
    public abstract PrintStream getPrintStream();

    @Override
    public void write(int c)
            throws IOException {
        getPrintStream().print((char) c);
    }

    @Override
    public void write(char[] chars, int off, int len)
            throws IOException {
        getPrintStream().print(Arrays.copyOfRange(chars, off, off + len));
    }

    @Override
    public void write(String string, int off, int len)
            throws IOException {
        getPrintStream().print(string.substring(off, off + len));
    }

    @Override
    public void println() {
        getPrintStream().println();
    }

    @Override
    public void print(Object obj) {
        getPrintStream().print(obj);
    }

    @Override
    public void print(String s) {
        getPrintStream().print(s);
    }

    @Override
    protected void _flush(boolean strict)
            throws IOException {
        getPrintStream().flush();
    }

    @Override
    protected void _close()
            throws IOException {
        getPrintStream().close();
    }

}
