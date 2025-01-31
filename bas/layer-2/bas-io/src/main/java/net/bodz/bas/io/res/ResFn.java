package net.bodz.bas.io.res;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.io.res.builtin.StringSource;
import net.bodz.bas.io.res.builtin.URLResource;

public class ResFn {

    public static StringSource string(String s) {
        return new StringSource(s);
    }

    public static FileResource file(File file) {
        return new FileResource(file);
    }

    public static FileResource file(File file, String encoding) {
        return new FileResource(file).charset(encoding);
    }

    public static FileResource file(File file, Charset charset) {
        return new FileResource(file).charset(charset);
    }

    public static FileResource file(String file) {
        return new FileResource(file);
    }

    public static FileResource file(String file, String encoding) {
        return new FileResource(file).charset(encoding);
    }

    public static FileResource file(String file, Charset charset) {
        return new FileResource(file).charset(charset);
    }

    public static URLResource url(URL url) {
        return new URLResource(url);
    }

    public static URLResource url(URL url, String encoding) {
        return new URLResource(url).charset(encoding);
    }

    public static URLResource url(URL url, Charset charset) {
        return new URLResource(url).charset(charset);
    }

    public static URLResource url(String url)
            throws MalformedURLException {
        return new URLResource(url);
    }

    public static URLResource url(String url, String encoding)
            throws MalformedURLException {
        return new URLResource(url).charset(encoding);
    }

    public static URLResource url(String url, Charset charset)
            throws MalformedURLException {
        return new URLResource(url).charset(charset);
    }

}
