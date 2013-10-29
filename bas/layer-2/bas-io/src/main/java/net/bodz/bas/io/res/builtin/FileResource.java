package net.bodz.bas.io.res.builtin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.OpenOption;

import net.bodz.bas.c.java.nio.CreateOptions;
import net.bodz.bas.c.java.nio.OpenOptions;
import net.bodz.bas.io.ICroppable;
import net.bodz.bas.io.ISeekable;
import net.bodz.bas.io.res.AbstractIORandomResource;

public class FileResource
        extends AbstractIORandomResource {

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
    public long getLength() {
        return file.length();
    }

    @Override
    protected void beforeOpenOutput(OpenOption... options)
            throws IOException {
        super.beforeOpenOutput(options);
        if (CreateOptions.isCreateParents(options)) {
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.isDirectory()) {
                if (!parentFile.mkdirs())
                    // throw ...?
                    ;
            }
        }
    }

    @Override
    protected InputStream _newInputStream(OpenOption... options)
            throws IOException {
        return new FileInputStream(file);
    }

    @Override
    protected OutputStream _newOutputStream(OpenOption... options)
            throws IOException {
        boolean append = OpenOptions.isAppend(options);
        return new FileOutputStream(file, append);
    }

    // XXX byte/char IOS, seeker, cropper

    @Override
    protected ISeekable getSeeker() {
        return null;
    }

    @Override
    protected ICroppable getCropper() {
        return null;
    }

}
