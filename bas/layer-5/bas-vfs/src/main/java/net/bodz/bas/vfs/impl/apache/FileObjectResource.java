package net.bodz.bas.vfs.impl.apache;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;

import org.apache.commons.vfs.FileContent;
import org.apache.commons.vfs.FileObject;

import net.bodz.bas.c.java.nio.OpenOptions;
import net.bodz.bas.io.ICroppable;
import net.bodz.bas.io.ISeekable;
import net.bodz.bas.io.res.AbstractIORandomResource;
import net.bodz.bas.meta.decl.NotNull;

import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.FileType;

public class FileObjectResource
        extends AbstractIORandomResource {

    private final FileObject fileObject;

    public FileObjectResource(FileObject fileObject) {
        if (fileObject == null)
            throw new NullPointerException("fileObject");
        this.fileObject = fileObject;
    }

    @Override
    public String getName() {
        return fileObject.getName().getBaseName();
    }

    @Override
    public boolean canRead() {
        try {
            return fileObject.isReadable();
        } catch (FileSystemException e) {
            return false;
        }
    }

    @Override
    public boolean canWrite() {
        try {
            return fileObject.isWriteable();
        } catch (FileSystemException e) {
            return false;
        }
    }

    @Override
    public boolean isDirectory(LinkOption... options) {
        try {
            return fileObject.getType() == FileType.FOLDER;
        } catch (FileSystemException e) {
            return false;
        }
    }

    @NotNull
    @Override
    public InputStream newInputStream(OpenOption... options)
            throws IOException {
        FileContent content = fileObject.getContent();
        checkOpen(content);
        InputStream in = content.getInputStream();
        return in;
    }

    @NotNull
    @Override
    public OutputStream newOutputStream(OpenOption... options)
            throws IOException {
        FileContent content = fileObject.getContent();
        checkOpen(content);

        boolean append = OpenOptions.isAppend(options);

        OutputStream out = content.getOutputStream(append);
        return out;
    }

    void checkOpen(FileContent fileContent) {
        if (fileContent.isOpen())
            throw new IllegalStateException("File is already opened: " + fileObject.getName());
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