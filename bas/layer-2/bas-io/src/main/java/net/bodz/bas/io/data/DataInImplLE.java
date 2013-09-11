package net.bodz.bas.io.data;

import java.io.IOException;

import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IDataIn;

public class DataInImplLE
        extends AbstractDataInLE
        implements IDataIn {

    private final IByteIn baseImpl;

    DataInImplLE(IByteIn baseImpl) {
        if (baseImpl == null)
            throw new NullPointerException("baseImpl");
        this.baseImpl = baseImpl;
    }

    public static IDataIn from(IByteIn byteIn) {
        if (byteIn instanceof IDataIn)
            return (IDataIn) byteIn;
        else
            return new DataInImplLE(byteIn);
    }

    @Override
    public long skip(long n)
            throws IOException {
        return baseImpl.skip(n);
    }

    @Override
    public int read(byte[] buf, int off, int len)
            throws IOException {
        return baseImpl.read(buf, off, len);
    }

    @Override
    public int read()
            throws IOException {
        return baseImpl.read();
    }

    @Override
    public void close()
            throws IOException {
        baseImpl.close();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DataInImplLE))
            return false;
        DataInImplLE o = (DataInImplLE) obj;
        if (!baseImpl.equals(o.baseImpl))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0x9b81fc3d;
        hash += baseImpl.hashCode();
        return hash;
    }

}
