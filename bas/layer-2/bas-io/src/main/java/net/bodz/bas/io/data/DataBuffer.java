package net.bodz.bas.io.data;

import net.bodz.bas.io.BByteOut;
import net.bodz.bas.io.DecoratedDataOut;
import net.bodz.bas.io.IDataOut;

public class DataBuffer
        extends DecoratedDataOut {

    private static final long serialVersionUID = 1L;

    BByteOut buf;

    public DataBuffer(boolean bigEndian) {
        this(new BByteOut(), bigEndian);
    }

    public DataBuffer(boolean bigEndian, int initialSize) {
        this(new BByteOut(initialSize), bigEndian);
    }

    public DataBuffer(BByteOut buf, boolean bigEndian) {
        super(bigEndian ? new DataOutImplBE(buf) //
                : new DataOutImplLE(buf));
        this.buf = buf;
    }

    public DataBuffer(BByteOut buf, IDataOut dataOut) {
        super(dataOut);
        this.buf = buf;
    }

    public byte[] toByteArray() {
        return buf.getBuffer().toByteArray();
    }

}
