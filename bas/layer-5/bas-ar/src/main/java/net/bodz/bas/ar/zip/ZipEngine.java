package net.bodz.bas.ar.zip;

import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.io.ICloseable;

public abstract class ZipEngine
        implements IZipContext, IZipConsts, IZip64Consts, ICloseable {

    private Charset charset = Charset.defaultCharset();
    private String password = "";

    private boolean closed;

    /** ⇱ Implementation Of {@link IZipContext}. */
    ;

    @Override
    public Charset getZipCharset() {
        return charset;
    }

    public void setZipCharset(Charset zipCharset) {
        if (zipCharset == null)
            throw new NullPointerException("zipCharset");
        this.charset = zipCharset;
    }

    @Override
    public String getZipPassword() {
        return password;
    }

    public void setZipPassword(String password) {
        if (password == null)
            password = "";
        this.password = password;
    }

    @Override
    public void requireZipVersion(short version) {
        if (version > VN_Deflate64)
            throw new UnsupportedOperationException("Version too high to handle: " + version);
    }

    @Override
    public ZipArchiver getArchiver() {
        throw new IllegalUsageException();
    }

    @Override
    public ZipUnarchiver getUnarchiver() {
        throw new IllegalUsageException();
    }

    /** ⇱ Implementation Of {@link ICloseable}. */
    ;

    @Override
    public void close()
            throws IOException {
        closed = true;
    }

    @Override
    public boolean isClosed() {
        return closed;
    }

}
