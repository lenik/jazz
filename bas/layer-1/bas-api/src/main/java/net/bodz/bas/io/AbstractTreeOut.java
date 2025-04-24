package net.bodz.bas.io;

import java.io.IOException;
import java.io.Writer;

public abstract class AbstractTreeOut
        extends Writer
        implements ITreeOut {

    private final ITextIndention textIndention;
    private boolean linePrefixPrinted;

    public AbstractTreeOut() {
        this(new TextIndention());
    }

    /**
     * @throws NullPointerException If <code>textIndention</code> is <code>null</code>.
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
        ITreeOut.super.print(s);
        if (s.endsWith("\n"))
            linePrefixPrinted = false;
    }

    @Override
    public void print(char c) {
        flushPrefix();
        ITreeOut.super.print(c);
        if (c == '\n')
            linePrefixPrinted = false;
    }

    @Override
    public void print(char[] s) {
        flushPrefix();
        if (s.length > 0) {
            ITreeOut.super.print(s);
            if (s[s.length - 1] == '\n')
                linePrefixPrinted = false;
        }
    }

    @Override
    public void print(Object... args) {
        flushPrefix();
        ITreeOut.super.print(args);
    }

    @Override
    public void println(char x) {
        flushPrefix();
        ITreeOut.super.println(x);
        linePrefixPrinted = false;
    }

    @Override
    public void println(char[] x) {
        flushPrefix();
        ITreeOut.super.println(x);
        linePrefixPrinted = false;
    }

    @Override
    public void println(Object... args) {
        flushPrefix();
        ITreeOut.super.println(args);
        linePrefixPrinted = false;
    }

    @Override
    public void println(String x) {
        flushPrefix();
        ITreeOut.super.println(x);
        linePrefixPrinted = false;
    }

    @Override
    public void println() {
        ITreeOut.super.println();
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

    @Override
    public void flush() {
    }

    @Override
    public void close()
            throws IOException {
        flush();
    }

}