package net.bodz.bas.io.resource.builtin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.jar.JarFile;
import java.util.zip.ZipFile;

import net.bodz.bas.io.resource.IRandomInputSource;
import net.bodz.bas.io.resource.IRandomOutputTarget;
import net.bodz.bas.io.resource.JavaioStreamResource;
import net.bodz.bas.util.exception.BadFormatException;

public class LocalFileResource
        extends JavaioStreamResource
        implements IRandomInputSource, IRandomOutputTarget {

    private final File file;

    public LocalFileResource(File file) {
        if (file == null)
            throw new NullPointerException("file");
        this.file = file;
    }

    public LocalFileResource(File file, Charset charset) {
        this(file);
        this.setCharset(charset);
    }

    public LocalFileResource(File file, String charsetName) {
        this(file);
        this.setCharset(charsetName);
    }

    public LocalFileResource(String filename) {
        this(new File(filename));
    }

    public LocalFileResource(String filename, Charset charset) {
        this(new File(filename), charset);
    }

    public LocalFileResource(String filename, String charsetName) {
        this(new File(filename), charsetName);
    }

    @Override
    public InputStream newInputStream()
            throws IOException {
        return new FileInputStream(file);
    }

    @Override
    public OutputStream newOutputStream()
            throws IOException {
        return new FileOutputStream(file, isAppendMode());
    }

    // private AbstractRandomInputSource arisImpl;
    // private AbstractRandomOutputTarget arotImpl;

    @Override
    public JarFile newJarFile()
            throws BadFormatException, IOException {
        return new JarFile(file);
    }

    @Override
    public ZipFile newZipFile()
            throws BadFormatException, IOException {
        return new ZipFile(file);
    }

}
