package net.bodz.bas.sio;

import java.io.IOException;
import java.nio.CharBuffer;

public class PrintOutImpl
        extends AbstractPrintOut {

    private final ICharOut base;

    /**
     * @throws NullPointerException
     *             If <code>base</code> is <code>null</code>.
     */
    public PrintOutImpl(ICharOut base) {
        if (base == null)
            throw new NullPointerException("base");
        this.base = base;
    }

    @Override
    public void write(int c)
            throws IOException {
        base.write(c);
    }

    @Override
    public void write(char[] chars, int off, int len)
            throws IOException {
        base.write(chars, off, len);
    }

    @Override
    public void write(CharSequence chars, int off, int len)
            throws IOException {
        base.write(chars, off, len);
    }

    @Override
    public void write(String string, int off, int len)
            throws IOException {
        base.write(string, off, len);
    }

    @Override
    public void write(CharBuffer charBuffer)
            throws IOException {
        base.write(charBuffer);
    }

    @Override
    protected void _flush(boolean strict)
            throws IOException {
        base.flush(strict);
    }

    @Override
    protected void _close()
            throws IOException {
        base.close();
    }

    @Override
    public int hashCode() {
        int hash = 0xe4b6e81b;
        hash += base.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PrintOutImpl))
            return false;
        PrintOutImpl o = (PrintOutImpl) obj;
        if (!base.equals(o.base))
            return false;
        return true;
    }

}
