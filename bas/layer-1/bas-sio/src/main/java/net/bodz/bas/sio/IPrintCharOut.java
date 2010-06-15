package net.bodz.bas.sio;

import java.io.IOException;
import java.util.Locale;

public interface IPrintCharOut
        extends ICharOut {

    /**
     * @param reset
     *            <code>true</code> the error state will be cleared.
     */
    void checkError(boolean reset)
            throws IOException;

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

    void println(String x);

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
    void flush(boolean strict);

    @Override
    void flush();

    @Override
    void close();

}
