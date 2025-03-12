package net.bodz.bas.io.res;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Path;

import net.bodz.bas.io.res.builtin.ByteArrayResource;
import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.io.res.builtin.PathResource;
import net.bodz.bas.io.res.builtin.StringSource;
import net.bodz.bas.io.res.builtin.URLResource;

public class ResFn {

    public static StringSource string(String s) {
        return new StringSource(s);
    }

    public static StringSource string(String s, String encoding) {
        return new StringSource(s).charset(encoding);
    }

    public static StringSource string(String s, Charset charset) {
        return new StringSource(s).charset(charset);
    }

    public static ByteArrayResource bytes(byte[] buf) {
        return bytes(buf, 0, buf.length);
    }

    public static ByteArrayResource bytes(byte[] buf, int off, int len) {
        return new ByteArrayResource(buf, off, len);
    }

    public static PathResource path(Path path) {
        return new PathResource(path);
    }

    public static PathResource path(Path path, String encoding) {
        return new PathResource(path).charset(encoding);
    }

    public static PathResource path(Path path, Charset charset) {
        return new PathResource(path).charset(charset);
    }

    public static PathResource path(String path) {
        return new PathResource(path);
    }

    public static PathResource path(String path, String encoding) {
        return new PathResource(path).charset(encoding);
    }

    public static PathResource path(String path, Charset charset) {
        return new PathResource(path).charset(charset);
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
