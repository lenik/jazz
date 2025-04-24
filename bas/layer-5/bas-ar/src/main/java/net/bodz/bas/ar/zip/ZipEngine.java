package net.bodz.bas.ar.zip;

import java.nio.charset.Charset;

import net.bodz.bas.err.IllegalUsageException;

public abstract class ZipEngine
        implements IZipContext,
                   IZipConsts,
                   IZip64Consts {

    private Charset charset = Charset.defaultCharset();
    private String password = "";

    /**
     * ⇱ Implementation Of {@link IZipContext}.
     */
    /* _____________________________ */static section.iface __CONTEXT__;

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

}
