package net.bodz.bas.io.res;

import java.io.File;
import java.nio.charset.Charset;

import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.io.res.builtin.StringSource;

public class ResFn {

    public static FileResource file(File file) {
        return ResFn.file(file);
    }

    public static FileResource file(File file, String encoding) {
        return ResFn.file(file, encoding);
    }

    public static FileResource file(File file, Charset charset) {
        return ResFn.file(file, charset);
    }

    public static FileResource file(String file) {
        return ResFn.file(file);
    }

    public static FileResource file(String file, String encoding) {
        return ResFn.file(file, encoding);
    }

    public static FileResource file(String file, Charset charset) {
        return ResFn.file(file, charset);
    }

    public static StringSource string(String s) {
        return new StringSource(s);
    }

}
