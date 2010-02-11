package net.bodz.bas.sio.indent;

import java.io.IOException;
import java.util.Locale;

import net.bodz.bas.sio.ILineCharOut;
import net.bodz.bas.sio.LineCharOutImpl;
import net.bodz.bas.sio.SIOException;

/**
 * @test {@link IndentedCharOutImplTest}
 */
public class IndentedCharOutImpl
        extends LineCharOutImpl
        implements IIndentedCharOut {

    private final ITextIndention textIndention;
    private boolean linePrefixPrinted;

    public IndentedCharOutImpl(ILineCharOut charOut) {
        this(charOut, new TextIndention());
    }

    /**
     * @throws NullPointerException
     *             If any argument is <code>null</code>.
     */
    public IndentedCharOutImpl(ILineCharOut charOut, ITextIndention textIndention) {
        super(charOut);
        this.textIndention = textIndention;
    }

    void flushPrefix()
            throws SIOException {
        if (linePrefixPrinted)
            return;
        try {
            write(textIndention.getCurrentLinePrefix());
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
        return textIndention.getIndentLevel();
    }

    @Override
    public void setIndentLevel(int indentLevel) {
        textIndention.setIndentLevel(indentLevel);
    }

    @Override
    public void increaseIndentLevel() {
        textIndention.increaseIndentLevel();
    }

    @Override
    public void decreaseIndentLevel() {
        textIndention.decreaseIndentLevel();
    }

    @Override
    public int getIndentSize() {
        return textIndention.getIndentSize();
    }

    @Override
    public void setIndentSize(int indentSize) {
        textIndention.setIndentSize(indentSize);
    }

    @Override
    public int getTabSize() {
        return textIndention.getTabSize();
    }

    @Override
    public void setTabSize(int tabSize) {
        textIndention.setTabSize(tabSize);
    }

    @Override
    public boolean isMixedMode() {
        return textIndention.isMixedMode();
    }

    @Override
    public void setMixedMode(boolean mixedMode) {
        textIndention.setMixedMode(mixedMode);
    }

    @Override
    public String getCurrentLinePrefix() {
        return textIndention.getCurrentLinePrefix();
    }

    @Override
    public void setCurrentLinePrefix(String currentLinePrefix) {
        textIndention.setCurrentLinePrefix(currentLinePrefix);
    }

}
