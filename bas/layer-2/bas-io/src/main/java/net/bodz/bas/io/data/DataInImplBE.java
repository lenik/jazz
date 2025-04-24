package net.bodz.bas.io.data;

import java.io.IOException;
import java.io.InputStream;

import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.adapter.InputStreamByteIn;
import net.bodz.bas.meta.decl.NotNull;

public class DataInImplBE
        extends AbstractDataInBE
        implements IDataIn {

    private final IByteIn baseImpl;

    DataInImplBE(IByteIn baseImpl) {
        if (baseImpl == null)
            throw new NullPointerException("baseImpl");
        this.baseImpl = baseImpl;
    }

    public static DataInImplBE from(IByteIn byteIn) {
        if (byteIn instanceof DataInImplBE)
            return (DataInImplBE) byteIn;
        else
            return new DataInImplBE(byteIn);
    }

    public static DataInImplBE from(InputStream in) {
        IByteIn byteIn = new InputStreamByteIn(in);
        return from(byteIn);
    }

    @Override
    public long skip(long n)
            throws IOException {
        return baseImpl.skip(n);
    }

    @Override
    public int read(@NotNull byte[] buf, int off, int len)
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
        if (!(obj instanceof DataInImplBE))
            return false;
        DataInImplBE o = (DataInImplBE) obj;
        if (!baseImpl.equals(o.baseImpl))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0xb22ab07c;
        hash += baseImpl.hashCode();
        return hash;
    }

}
