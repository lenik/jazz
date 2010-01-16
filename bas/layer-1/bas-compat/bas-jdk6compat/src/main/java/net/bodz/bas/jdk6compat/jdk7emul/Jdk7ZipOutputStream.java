package net.bodz.bas.jdk6compat.jdk7emul;

import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipOutputStream;

public class Jdk7ZipOutputStream {

    public static ZipOutputStream newInstance(OutputStream out, Charset charset) {
        // ignore charset!
        return new ZipOutputStream(out);
    }

}
