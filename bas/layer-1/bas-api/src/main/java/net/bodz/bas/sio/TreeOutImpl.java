package net.bodz.bas.sio;

import java.io.IOException;
import java.util.Locale;

import net.bodz.bas.sio.util.ITextIndention;
import net.bodz.bas.sio.util.TextIndention;

public class TreeOutImpl
        extends AbstractPrintOut
        implements ITreeOut {

    private IPrintOut printOut;
    private final ITextIndention textIndention;
    private boolean linePrefixPrinted;

    TreeOutImpl(ICharOut charOut) {
        this(new PrintOutImpl(charOut), new TextIndention());
    }

    TreeOutImpl(IPrintOut printOut) {
        this(printOut, new TextIndention());
    }

    public static ITreeOut from(IPrintOut charOut) {
        if (charOut instanceof ITreeOut)
            return (ITreeOut) charOut;
        else
            return new TreeOutImpl((IPrintOut) charOut);
    }

    public static ITreeOut from(ICharOut charOut) {
        if (charOut instanceof IPrintOut)
            return from((IPrintOut) charOut);
        else
            return new TreeOutImpl(charOut);
    }

    /**
     * Print out.
     * 
     * @throws NullPointerException
     *             If any argument is <code>null</code>.
     */
    public TreeOutImpl(IPrintOut printOut, ITextIndention textIndention) {
        if (printOut == null)
            throw new NullPointerException("printOut");
        if (textIndention == null)
            throw new NullPointerException("textIndention");
        this.printOut = printOut;
        this.textIndention = textIndention;
    }

    void flushPrefix() {
        if (linePrefixPrinted)
            return;
        try {
            printOut.write(textIndention.getCurrentLinePrefix());
            linePrefixPrinted = true;
        } catch (IOException e) {
            throw new PrintException(e);
        }
    }

    // --o IStackedOut

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

    // --o IPrint

    @Override
    public void print(String s) {
        flushPrefix();
        printOut.print(s);
    }

    @Override
    public void print(boolean b) {
        flushPrefix();
        printOut.print(b);
    }

    @Override
    public void print(char c) {
        flushPrefix();
        printOut.print(c);
    }

    @Override
    public void print(char[] s) {
        flushPrefix();
        printOut.print(s);
    }

    @Override
    public void print(double d) {
        flushPrefix();
        printOut.print(d);
    }

    @Override
    public void print(float f) {
        flushPrefix();
        printOut.print(f);
    }

    @Override
    public void print(int i) {
        flushPrefix();
        printOut.print(i);
    }

    @Override
    public void print(long l) {
        flushPrefix();
        printOut.print(l);
    }

    @Override
    public void print(Object... args) {
        flushPrefix();
        printOut.print(args);
    }

    @Override
    public void print(Object obj) {
        flushPrefix();
        printOut.print(obj);
    }

    @Override
    public void printf(Locale l, String format, Object... args) {
        flushPrefix();
        printOut.printf(l, format, args);
    }

    @Override
    public void printf(String format, Object... args) {
        flushPrefix();
        printOut.printf(format, args);
    }

    @Override
    public void println(boolean x) {
        flushPrefix();
        printOut.println(x);
        linePrefixPrinted = false;
    }

    @Override
    public void println(char x) {
        flushPrefix();
        printOut.println(x);
        linePrefixPrinted = false;
    }

    @Override
    public void println(char[] x) {
        flushPrefix();
        printOut.println(x);
        linePrefixPrinted = false;
    }

    @Override
    public void println(double x) {
        flushPrefix();
        printOut.println(x);
        linePrefixPrinted = false;
    }

    @Override
    public void println(float x) {
        flushPrefix();
        printOut.println(x);
        linePrefixPrinted = false;
    }

    @Override
    public void println(int x) {
        flushPrefix();
        printOut.println(x);
        linePrefixPrinted = false;
    }

    @Override
    public void println(long x) {
        flushPrefix();
        printOut.println(x);
        linePrefixPrinted = false;
    }

    @Override
    public void println(Object... args) {
        flushPrefix();
        printOut.println(args);
        linePrefixPrinted = false;
    }

    @Override
    public void println(Object x) {
        flushPrefix();
        printOut.println(x);
        linePrefixPrinted = false;
    }

    @Override
    public void println(String x) {
        flushPrefix();
        printOut.println(x);
        linePrefixPrinted = false;
    }

    @Override
    public void println() {
        printOut.println();
        linePrefixPrinted = false;
    }

    // --o ICharOut

    @Override
    public void write(char[] chars, int off, int len)
            throws IOException {
        printOut.write(chars, off, len);
    }

    @Override
    public void write(int ch)
            throws IOException {
        printOut.write(ch);
    }

}
