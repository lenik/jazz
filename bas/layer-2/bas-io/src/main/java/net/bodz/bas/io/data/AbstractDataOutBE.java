package net.bodz.bas.io.data;

import java.io.IOException;

import net.bodz.bas.io.IDataOut;

public abstract class AbstractDataOutBE
        extends AbstractDataOut
        implements
            IDataOut {

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

}
