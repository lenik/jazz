package net.bodz.bas.io.resource.builtin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.OpenOption;

import net.bodz.bas.c.java.nio.CommonOpenConfig;
import net.bodz.bas.io.resource.AbstractInputOutputStreamResource;

public class FileResource
        extends AbstractInputOutputStreamResource {

    private final File file;

    public FileResource(File file) {
        if (file == null)
            throw new NullPointerException("file");
        this.file = file;
    }

    public FileResource(File file, Charset charset) {
        this(file);
        this.setCharset(charset);
    }

    public FileResource(File file, String charsetName) {
        this(file);
        this.setCharset(charsetName);
    }

    public FileResource(String filename) {
        this(new File(filename));
    }

    public FileResource(String filename, Charset charset) {
        this(new File(filename), charset);
    }

    public FileResource(String filename, String charsetName) {
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

}
