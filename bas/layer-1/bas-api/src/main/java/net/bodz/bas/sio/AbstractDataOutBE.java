package net.bodz.bas.sio;

import java.io.IOException;

public abstract class AbstractDataOutBE
        extends AbstractDataOut
        implements IDataOut {

    @Override
    public boolean isBigEndian() {
        return true;
    }

    @Override
    public void writeByte(int b)
            throws IOException {
        write(b);
    }

    @Override
    public void writeWord(int s)
            throws IOException {
        write(s >> 8);
        write(s);
    }

    @Override
    public void writeDword(int v)
            throws IOException {
        write(v >> 24);
        write(v >> 16);
        write(v >> 8);
        write(v);
    }

    @Override
    public void writeQword(long v)
            throws IOException {
        writeDword((int) (v >> 32));
        writeDword((int) v);
    }

    @Override
    public void writeString(int flags, String str, String encoding)
            throws IOException {
        if (str == null)
            throw new NullPointerException("str");
        _WriteUtfStringImpl _ws = new _WriteUtfStringImpl(flags, str, encoding);
        _ws.setBigEndian(true);
        _ws.write(this);
    }

}
