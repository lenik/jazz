package net.bodz.bas.io.data;

import java.io.IOException;

import net.bodz.bas.io.IDataOut;

public abstract class AbstractDataOutLE
        extends AbstractDataOut
        implements IDataOut {

    @Override
    public boolean isBigEndian() {
        return false;
    }

    @Override
    public void writeByte(int b)
            throws IOException {
        write(b);
    }

    @Override
    public synchronized void writeWord(int s)
            throws IOException {
        write(s);
        write(s >> 8);
    }

    @Override
    public synchronized void writeDword(int v)
            throws IOException {
        write(v);
        v >>= 8;
        write(v);
        v >>= 8;
        write(v);
        v >>= 8;
        write(v);
    }

    @Override
    public synchronized void writeQword(long v)
            throws IOException {
        writeDword((int) v);
        v >>= 32;
        writeDword((int) v);
    }

    @Override
    public synchronized void writeString(int flags, String str, String encoding)
            throws IOException {
        if (str == null)
            throw new NullPointerException("str");
        _WriteUtfStringImpl _ws = new _WriteUtfStringImpl(flags, str, encoding);
        _ws.setBigEndian(false);
        _ws.write(this);
    }

}
