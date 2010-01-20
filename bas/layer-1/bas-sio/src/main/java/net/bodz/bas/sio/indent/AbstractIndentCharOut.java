package net.bodz.bas.sio.indent;

import java.io.IOException;
import java.util.Locale;

import net.bodz.bas.sio.ILineCharOut;
import net.bodz.bas.sio.ProxyLineCharOut;
import net.bodz.bas.sio.SIOException;

public abstract class AbstractIndentCharOut
        extends ProxyLineCharOut
        implements IIndention {

    private TextIndention indention;
    private boolean linePrefixPrinted;

    public AbstractIndentCharOut(ILineCharOut proxy) {
        super(proxy);
    }

    void flushPrefix()
            throws SIOException {
        if (linePrefixPrinted)
            return;
        try {
            write(indention.getCurrentLinePrefix());
        } catch (IOException e) {
            throw new SIOException(e);
        }
        linePrefixPrinted = true;
    }

    @Override
    public void print(String s)
            throws SIOException {
        flushPrefix();
        super.print(s);
    }

    @Override
    public void print(boolean b)
            throws SIOException {
        flushPrefix();
        super.print(b);
    }

    @Override
    public void print(char c)
            throws SIOException {
        flushPrefix();
        super.print(c);
    }

    @Override
    public void print(char[] s)
            throws SIOException {
        flushPrefix();
        super.print(s);
    }

    @Override
    public void print(double d)
            throws SIOException {
        flushPrefix();
        super.print(d);
    }

    @Override
    public void print(float f)
            throws SIOException {
        flushPrefix();
        super.print(f);
    }

    @Override
    public void print(int i)
            throws SIOException {
        flushPrefix();
        super.print(i);
    }

    @Override
    public void print(long l)
            throws SIOException {
        flushPrefix();
        super.print(l);
    }

    @Override
    public void print(Object... args)
            throws SIOException {
        flushPrefix();
        super.print(args);
    }

    @Override
    public void print(Object obj)
            throws SIOException {
        flushPrefix();
        super.print(obj);
    }

    @Override
    public void printf(Locale l, String format, Object... args)
            throws SIOException {
        flushPrefix();
        super.printf(l, format, args);
    }

    @Override
    public void printf(String format, Object... args)
            throws SIOException {
        flushPrefix();
        super.printf(format, args);
    }

    @Override
    public void println(boolean x)
            throws SIOException {
        flushPrefix();
        super.println(x);
        linePrefixPrinted = false;
    }

    @Override
    public void println(char x)
            throws SIOException {
        flushPrefix();
        super.println(x);
        linePrefixPrinted = false;
    }

    @Override
    public void println(char[] x)
            throws SIOException {
        flushPrefix();
        super.println(x);
        linePrefixPrinted = false;
    }

    @Override
    public void println(double x)
            throws SIOException {
        flushPrefix();
        super.println(x);
        linePrefixPrinted = false;
    }

    @Override
    public void println(float x)
            throws SIOException {
        flushPrefix();
        super.println(x);
        linePrefixPrinted = false;
    }

    @Override
    public void println(int x)
            throws SIOException {
        flushPrefix();
        super.println(x);
        linePrefixPrinted = false;
    }

    @Override
    public void println(long x)
            throws SIOException {
        flushPrefix();
        super.println(x);
        linePrefixPrinted = false;
    }

    @Override
    public void println(Object... args)
            throws SIOException {
        flushPrefix();
        super.println(args);
        linePrefixPrinted = false;
    }

    @Override
    public void println(Object x)
            throws SIOException {
        flushPrefix();
        super.println(x);
        linePrefixPrinted = false;
    }

    @Override
    public void println(String x)
            throws SIOException {
        flushPrefix();
        super.println(x);
        linePrefixPrinted = false;
    }

    @Override
    public void println()
            throws SIOException {
        super.println();
        linePrefixPrinted = false;
    }

    @Override
    public int getIndentLevel() {
        return indention.getIndentLevel();
    }

    @Override
    public void setIndentLevel(int indentLevel) {
        indention.setIndentLevel(indentLevel);
    }

    @Override
    public void increaseIndentLevel() {
        indention.increaseIndentLevel();
    }

    @Override
    public void decreaseIndentLevel() {
        indention.decreaseIndentLevel();
    }

}
