package net.bodz.bas.io.impl;

import java.io.IOException;
import java.io.Writer;

import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.ITextIndention;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.PrintException;
import net.bodz.bas.io.TextIndention;
import net.bodz.bas.meta.decl.NotNull;

public class TreeOutImpl
        extends Writer
        implements ITreeOut {

    private IPrintOut printOut;
    private ITextIndention textIndention;
    private boolean linePrefixPrinted;

    protected TreeOutImpl(IPrintOut printOut) {
        this(printOut, new TextIndention());
    }

    /**
     * Print out.
     *
     * @throws NullPointerException If any argument is <code>null</code>.
     */
    public TreeOutImpl(IPrintOut printOut, ITextIndention textIndention) {
        if (printOut == null)
            throw new NullPointerException("printOut");
        if (textIndention == null)
            throw new NullPointerException("textIndention");
        this.printOut = printOut;
        this.textIndention = textIndention;
    }

    public static ITreeOut from(ITreeOut treeOut) {
        return treeOut;
    }

    public static ITreeOut from(IPrintOut printOut) {
        if (printOut instanceof ITreeOut)
            return (ITreeOut) printOut;
        else
            return new TreeOutImpl(printOut);
    }

    public static ITreeOut from(ICharOut charOut) {
        if (charOut instanceof ITreeOut)
            return ((ITreeOut) charOut);
        else
            return new TreeOutImpl(PrintOutImpl.from(charOut));
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

    /**
     * ⇱ Implementaton Of {@link ITreeOut}.
     */
    /* _____________________________ */static section.iface __TREE__;

    @Override
    public ITextIndention getTextIndention() {
        return textIndention;
    }

    public void setTextIndention(ITextIndention textIndention) {
        this.textIndention = textIndention;
    }

    @Override
    public int enter() {
        return textIndention.increaseIndentLevel();
    }

    @Override
    public int leave() {
        return textIndention.decreaseIndentLevel();
    }

    /**
     * ⇱ Implementaton Of {@link IPrintOut}.
     */
    /* _____________________________ */static section.iface __PRINT__;

    @Override
    public void print(String s) {
        flushPrefix();
        printOut.print(s);
        if (s != null && s.endsWith("\n"))
            linePrefixPrinted = false;
    }

    @Override
    public void print(char c) {
        flushPrefix();
        printOut.print(c);
        if (c == '\n')
            linePrefixPrinted = false;
    }

    @Override
    public void print(char[] s) {
        flushPrefix();
        if (s.length > 0) {
            printOut.print(s);
            if (s[s.length - 1] == '\n')
                linePrefixPrinted = false;
        }
    }

    @Override
    public void print(Object... args) {
        flushPrefix();
        for (Object arg : args)
            print(arg);
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
    public void println(Object... args) {
        flushPrefix();
        printOut.println(args);
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

    /**
     * ⇱ Implementaton Of {@link ICharOut}.
     */
    /* _____________________________ */static section.iface __CHAR__;

    @Override
    public void write(int c)
            throws IOException {
        printOut.writeChar(c);
    }

    @Override
    public void writeChar(int ch)
            throws IOException {
        printOut.writeChar(ch);
    }

    @Override
    public void write(@NotNull char[] chars, int off, int len)
            throws IOException {
        printOut.write(chars, off, len);
    }

    @Override
    public void flush() {
        printOut.flush();
    }

    @Override
    public void close()
            throws IOException {
        printOut.close();
    }

    @Override
    public int hashCode() {
        int hash = 0x9b675cd7;
        hash += printOut.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof TreeOutImpl))
            return false;
        TreeOutImpl o = (TreeOutImpl) obj;
        if (!printOut.equals(o.printOut))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return printOut.toString();
    }

}
