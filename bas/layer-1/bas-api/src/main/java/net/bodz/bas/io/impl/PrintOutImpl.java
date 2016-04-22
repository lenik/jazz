package net.bodz.bas.io.impl;

import java.io.IOException;
import java.nio.CharBuffer;

import net.bodz.bas.io.AbstractPrintOut;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.IPrintOut;

public class PrintOutImpl
        extends AbstractPrintOut {

    protected final ICharOut baseImpl;

    /**
     * IPrintOut completion from ICharOut implementation.
     * 
     * @param baseImpl
     *            The {@link ICharOut} implementation, not-<code>null</code>.
     */
    protected PrintOutImpl(ICharOut baseImpl) {
        if (baseImpl == null)
            throw new NullPointerException("baseImpl");
        this.baseImpl = baseImpl;
    }

    public static IPrintOut from(IPrintOut printOut) {
        return printOut;
    }

    public static IPrintOut from(ICharOut charOut) {
        if (charOut instanceof IPrintOut)
            return (IPrintOut) charOut;
        else
            return new PrintOutImpl(charOut);
    }

    public ICharOut getBaseImpl() {
        return baseImpl;
    }

    @Override
    public void write(int c)
            throws IOException {
        baseImpl.write(c);
    }

    @Override
    public void write(char[] chars, int off, int len)
            throws IOException {
        baseImpl.write(chars, off, len);
    }

    @Override
    public void write(CharSequence chars, int start, int end)
            throws IOException {
        baseImpl.write(chars, start, end);
    }

    @Override
    public void write(String string, int off, int len)
            throws IOException {
        baseImpl.write(string, off, len);
    }

    @Override
    public void write(CharBuffer charBuffer)
            throws IOException {
        baseImpl.write(charBuffer);
    }

    @Override
    protected void _flush(boolean strict)
            throws IOException {
        baseImpl.flush(strict);
    }

    @Override
    protected void _close()
            throws IOException {
        baseImpl.close();
    }

    @Override
    public int hashCode() {
        int hash = 0xe4b6e81b;
        hash += baseImpl.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof PrintOutImpl))
            return false;
        PrintOutImpl o = (PrintOutImpl) obj;
        if (!baseImpl.equals(o.baseImpl))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return baseImpl.toString();
    }

}
