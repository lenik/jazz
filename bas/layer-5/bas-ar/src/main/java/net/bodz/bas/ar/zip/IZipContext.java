package net.bodz.bas.ar.zip;

import java.nio.charset.Charset;

public interface IZipContext {

    Charset getZipCharset();

    String getZipPassword();

    void requireZipVersion(short version);

    ZipArchiver getArchiver();

    ZipUnarchiver getUnarchiver();

}
