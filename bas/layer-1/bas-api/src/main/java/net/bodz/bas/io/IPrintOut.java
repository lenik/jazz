package net.bodz.bas.io;

import java.io.PrintWriter;
import java.util.Locale;

import net.bodz.bas.io.impl.NullPrintOut;
import net.bodz.bas.io.impl.TreeOutImpl;

public interface IPrintOut
        extends
            ICharOut {

    void print(CharSequence s);

    void print(String s);

    void print(boolean b);

    void print(char c);

    /**
     * @throws NullPointerException
     *             If <code>s</code> is <code>null</code>.
     */
    void print(char[] s);

    void print(double d);

    void print(float f);

    void print(int i);

    void print(long l);

    void print(Object obj);

    /**
     * @throws NullPointerException
     *             If <code>args</code> is <code>null</code>.
     */
    void print(Object... args);

    void println();

    void println(CharSequence x);

    void println(String x);

    void println(boolean x);

    void println(char x);

    /**
     * @throws NullPointerException
     *             If <code>x</code> is <code>null</code>.
     */
    void println(char[] x);

    void println(double x);

    void println(float x);

    void println(int x);

    void println(long x);

    void println(Object x);

    /**
     * @throws NullPointerException
     *             If <code>args</code> is <code>null</code>.
     */
    void println(Object... args);

    /**
     * @throws NullPointerException
     *             If any argument is <code>null</code>.
     */
    void printf(Locale l, String format, Object... args);

    /**
     * @throws NullPointerException
     *             If any argument is <code>null</code>.
     */
    void printf(String format, Object... args);

    @Override
    void flush(boolean sync);

    @Override
    void flush();

    @Override
    void close();

    default ITreeOut indented() {
        return TreeOutImpl.from(this);
    }

    PrintWriter toPrintWriter();

    IPrintOut NULL = new NullPrintOut();

}
