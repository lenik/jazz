package net.bodz.bas.io.adapter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.meta.decl.NotNull;

public abstract class DynamicPrintStreamPrintOut
        extends OutputStream
        implements IPrintOut {

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
    public void writeChar(int ch)
            throws IOException {
        getPrintStream().print((char) ch);
    }

    @Override
    public void write(@NotNull char[] chars, int off, int len)
            throws IOException {
        getPrintStream().print(Arrays.copyOfRange(chars, off, off + len));
    }

    @Override
    public void write(@NotNull String string, int off, int len)
            throws IOException {
        getPrintStream().print(string.substring(off, off + len));
    }

    @Override
    public void println() {
        getPrintStream().println();
    }

    @Override
    public void print(String s) {
        getPrintStream().print(s);
    }

    @Override
    public void flush() {
        getPrintStream().flush();
    }

    @Override
    public void close() {
        getPrintStream().close();
    }

}
