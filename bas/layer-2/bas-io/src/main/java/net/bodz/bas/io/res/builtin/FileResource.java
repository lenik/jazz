package net.bodz.bas.io.res.builtin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;

import net.bodz.bas.c.java.nio.CreateOptions;
import net.bodz.bas.io.ICroppable;
import net.bodz.bas.io.ISeekable;
import net.bodz.bas.io.res.AbstractIORandomResource;
import net.bodz.bas.meta.decl.DefaultImpl;
import net.bodz.bas.meta.decl.NotNull;

public class FileResource
        extends AbstractIORandomResource<FileResource> {

    @NotNull
    private final File file;

    public FileResource(@NotNull File file) {
        this.file = file;
    }

    public FileResource(@NotNull String filename) {
        this(new File(filename));
    }

    @Override
    public boolean canRead() {
        return file.canRead();
    }

    @Override
    public boolean canWrite() {
        return file.canWrite();
    }

    @Override
    public boolean isDirectory(LinkOption... options) {
        Path path = file.toPath();
        return Files.isDirectory(path, options);
    }

    @NotNull
    public File getFile() {
        return file;
    }

    @Override
    public boolean isPathPresent() {
        return true;
    }

    @NotNull
    @Override
    public String getPath() {
        return file.getPath();
    }

    @Override
    public Long getLength() {
        return file.length();
    }

    protected void beforeOpenOutput(OpenOption... options)
            throws IOException {
        if (CreateOptions.isCreateParents(options)) {
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.isDirectory()) {
                if (!parentFile.mkdirs())
                    // throw ...?
                    ;
            }
        }
    }

    @NotNull
    @DefaultImpl(Files.class)
    @Override
    public InputStream newInputStream(OpenOption... options)
            throws IOException {
        return Files.newInputStream(file.toPath());
    }

    @NotNull
    @DefaultImpl(Files.class)
    @Override
    public OutputStream newOutputStream(OpenOption... options)
            throws IOException {
        return Files.newOutputStream(file.toPath(), options);
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof FileResource))
            return false;
        FileResource o = (FileResource) obj;
        if (!file.equals(o.file))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0xd47cc0ba;
        hash += file.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return file.toString();
    }

}
