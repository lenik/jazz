package net.bodz.bas.sio;

import java.io.IOException;
import java.nio.CharBuffer;

public class PrintOutImpl
        extends AbstractPrintOut {

    protected final ICharOut charOut;

    /**
     * @throws NullPointerException
     *             If <code>base</code> is <code>null</code>.
     */
    public PrintOutImpl(ICharOut charOut) {
        if (charOut == null)
            throw new NullPointerException("charOut");
        this.charOut = charOut;
    }

    @Override
    public void write(int c)
            throws IOException {
        charOut.write(c);
    }

    @Override
    public void write(char[] chars, int off, int len)
            throws IOException {
        charOut.write(chars, off, len);
    }

    @Override
    public void write(CharSequence chars, int off, int len)
            throws IOException {
        charOut.write(chars, off, len);
    }

    @Override
    public void write(String string, int off, int len)
            throws IOException {
        charOut.write(string, off, len);
    }

    @Override
    public void write(CharBuffer charBuffer)
            throws IOException {
        charOut.write(charBuffer);
    }

    @Override
    protected void _flush(boolean strict)
            throws IOException {
        charOut.flush(strict);
    }

    @Override
    protected void _close()
            throws IOException {
        charOut.close();
    }

    @Override
    public int hashCode() {
        int hash = 0xe4b6e81b;
        hash += charOut.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PrintOutImpl))
            return false;
        PrintOutImpl o = (PrintOutImpl) obj;
        if (!charOut.equals(o.charOut))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return charOut.toString();
    }

}
