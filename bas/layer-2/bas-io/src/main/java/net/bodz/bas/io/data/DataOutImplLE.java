package net.bodz.bas.io.data;

import java.io.IOException;
import java.nio.ByteBuffer;

import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.IDataOut;
import net.bodz.bas.meta.decl.NotNull;

public class DataOutImplLE
        extends AbstractDataOutLE
        implements IDataOut {

    private final IByteOut baseImpl;

    DataOutImplLE(IByteOut baseImpl) {
        if (baseImpl == null)
            throw new NullPointerException("baseImpl");
        this.baseImpl = baseImpl;
    }

    public static IDataOut from(IByteOut byteOut) {
        if (byteOut instanceof IDataOut)
            return (IDataOut) byteOut;
        else
            return new DataOutImplLE(byteOut);
    }

    @Override
    public void write(int b)
            throws IOException {
        baseImpl.write(b);
    }

    @Override
    public void write(@NotNull byte[] buf, int off, int len)
            throws IOException {
        baseImpl.write(buf, off, len);
    }

    @Override
    public void write(ByteBuffer buffer)
            throws IOException {
        baseImpl.write(buffer);
    }

    @Override
    public void close()
            throws IOException {
        baseImpl.close();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DataOutImplLE))
            return false;
        DataOutImplLE o = (DataOutImplLE) obj;
        if (!baseImpl.equals(o.baseImpl))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0x2a8fd2d2;
        hash += baseImpl.hashCode();
        return hash;
    }

}
