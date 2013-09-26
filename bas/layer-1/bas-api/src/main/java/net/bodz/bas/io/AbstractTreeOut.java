package net.bodz.bas.io;

import java.io.IOException;
import java.util.Locale;

public abstract class AbstractTreeOut
        extends AbstractPrintOut
        implements ITreeOut {

    private final ITextIndention textIndention;
    private boolean linePrefixPrinted;

    public AbstractTreeOut() {
        this(new TextIndention());
    }

    /**
     * @throws NullPointerException
     *             If <code>textIndention</code> is <code>null</code>.
     */
    public AbstractTreeOut(ITextIndention textIndention) {
        if (textIndention == null)
            throw new NullPointerException("textIndention");
        this.textIndention = textIndention;
    }

    void flushPrefix() {
        if (linePrefixPrinted)
            return;
        try {
            write(textIndention.getCurrentLinePrefix());
            linePrefixPrinted = true;
        } catch (IOException e) {
            throw new PrintException(e);
        }
    }

    @Override
    public void print(String s) {
        flushPrefix();
        super.print(s);
    }

    @Override
    public void print(boolean b) {
        flushPrefix();
        super.print(b);
    }

    @Override
    public void print(char c) {
        flushPrefix();
        super.print(c);
    }

    @Override
    public void print(char[] s) {
        flushPrefix();
        super.print(s);
    }

    @Override
    public void print(double d) {
        flushPrefix();
        super.print(d);
    }

    @Override
    public void print(float f) {
        flushPrefix();
        super.print(f);
    }

    @Override
    public void print(int i) {
        flushPrefix();
        super.print(i);
    }

    @Override
    public void print(long l) {
        flushPrefix();
        super.print(l);
    }

    @Override
    public void print(Object... args) {
        flushPrefix();
        super.print(args);
    }

    @Override
    public void print(Object obj) {
        flushPrefix();
        super.print(obj);
    }

    @Override
    public void printf(Locale l, String format, Object... args) {
        flushPrefix();
        super.printf(l, format, args);
    }

    @Override
    public void printf(String format, Object... args) {
        flushPrefix();
        super.printf(format, args);
    }

    @Override
    public void println(boolean x) {
        flushPrefix();
        super.println(x);
        linePrefixPrinted = false;
    }

    @Override
    public void println(char x) {
        flushPrefix();
        super.println(x);
        linePrefixPrinted = false;
    }

    @Override
    public void println(char[] x) {
        flushPrefix();
        super.println(x);
        linePrefixPrinted = false;
    }

    @Override
    public void println(double x) {
        flushPrefix();
        super.println(x);
        linePrefixPrinted = false;
    }

    @Override
    public void println(float x) {
        flushPrefix();
        super.println(x);
        linePrefixPrinted = false;
    }

    @Override
    public void println(int x) {
        flushPrefix();
        super.println(x);
        linePrefixPrinted = false;
    }

    @Override
    public void println(long x) {
        flushPrefix();
        super.println(x);
        linePrefixPrinted = false;
    }

    @Override
    public void println(Object... args) {
        flushPrefix();
        super.println(args);
        linePrefixPrinted = false;
    }

    @Override
    public void println(Object x) {
        flushPrefix();
        super.println(x);
        linePrefixPrinted = false;
    }

    @Override
    public void println(String x) {
        flushPrefix();
        super.println(x);
        linePrefixPrinted = false;
    }

    @Override
    public void println() {
        super.println();
        linePrefixPrinted = false;
    }

    @Override
    public ITextIndention getTextIndention() {
        return textIndention;
    }

    @Override
    public int enter() {
        return textIndention.increaseIndentLevel();
    }

    @Override
    public int leave() {
        return textIndention.decreaseIndentLevel();
    }

}
