package net.bodz.bas.sio;

import java.util.Locale;

public interface ILineCharOut
        extends ICharOut {

    void print(String s)
            throws SIOException;

    void print(boolean b)
            throws SIOException;

    void print(char c)
            throws SIOException;

    /**
     * @throws NullPointerException
     *             If <code>s</code> is <code>null</code>.
     */
    void print(char[] s)
            throws SIOException;

    void print(double d)
            throws SIOException;

    void print(float f)
            throws SIOException;

    void print(int i)
            throws SIOException;

    void print(long l)
            throws SIOException;

    void print(Object obj)
            throws SIOException;

    /**
     * @throws NullPointerException
     *             If <code>args</code> is <code>null</code>.
     */
    void print(Object... args)
            throws SIOException;

    void println()
            throws SIOException;

    void println(boolean x)
            throws SIOException;

    void println(char x)
            throws SIOException;

    /**
     * @throws NullPointerException
     *             If <code>x</code> is <code>null</code>.
     */
    void println(char[] x)
            throws SIOException;

    void println(double x)
            throws SIOException;

    void println(float x)
            throws SIOException;

    void println(int x)
            throws SIOException;

    void println(long x)
            throws SIOException;

    void println(Object x)
            throws SIOException;

    void println(String x)
            throws SIOException;

    /**
     * @throws NullPointerException
     *             If <code>args</code> is <code>null</code>.
     */
    void println(Object... args)
            throws SIOException;

    /**
     * @throws NullPointerException
     *             If any argument is <code>null</code>.
     */
    void printf(Locale l, String format, Object... args)
            throws SIOException;

    /**
     * @throws NullPointerException
     *             If any argument is <code>null</code>.
     */
    void printf(String format, Object... args)
            throws SIOException;

}
