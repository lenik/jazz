package net.bodz.bas.io.resource.builtin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.OpenOption;
import java.util.jar.JarFile;
import java.util.zip.ZipFile;

import net.bodz.bas.c.java.nio.CommonOpenConfig;
import net.bodz.bas.err.BadFormatException;
import net.bodz.bas.io.resource.IRandomInputSource;
import net.bodz.bas.io.resource.IRandomOutputTarget;
import net.bodz.bas.io.resource.JavaioStreamResource;

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

    public File getFile() {
        return file;
    }

    @Override
    public Long getLength() {
        return file.length();
    }

    @Override
    protected InputStream _newInputStream(OpenOption... options)
            throws IOException {
        return new FileInputStream(file);
    }

    @Override
    protected OutputStream _newOutputStream(OpenOption... options)
            throws IOException {
        CommonOpenConfig config = CommonOpenConfig.parse(options);
        return new FileOutputStream(file, config.isAppend());
    }

    // private AbstractRandomInputSource arisImpl;
    // private AbstractRandomOutputTarget arotImpl;

    protected ZipFile _newZipFile(OpenOption... options)
            throws BadFormatException, IOException {
        return new ZipFile(file);
    }

    @Override
    public final ZipFile newZipFile(OpenOption... options)
            throws BadFormatException, IOException {
        return _newZipFile(options);
    }

    protected JarFile _newJarFile(OpenOption... options)
            throws BadFormatException, IOException {
        return new JarFile(file);
    }

    @Override
    public final JarFile newJarFile(OpenOption... options)
            throws BadFormatException, IOException {
        return _newJarFile(options);
    }

}
