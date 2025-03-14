package net.bodz.bas.io.res.builtin;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;

import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.FileType;
import org.apache.commons.vfs.VFS;

import net.bodz.bas.c.java.nio.OpenOptions;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.adapter.InputStreamByteIn;
import net.bodz.bas.io.adapter.OutputStreamByteOut;
import net.bodz.bas.io.adapter.ReaderCharIn;
import net.bodz.bas.io.adapter.WriterPrintOut;
import net.bodz.bas.io.res.AbstractStreamResource;
import net.bodz.bas.meta.decl.NotNull;

public class ApacheFileResource
        extends AbstractStreamResource<ApacheFileResource> {

    private final FileObject fileObject;

    public ApacheFileResource(FileObject vfile) {
        if (vfile == null)
            throw new NullPointerException("vfile");
        this.fileObject = vfile;
    }

    public ApacheFileResource(String vpath)
            throws FileSystemException {
        this(VFS.getManager().resolveFile(vpath));
    }

    public FileObject getFileObject() {
        return fileObject;
    }

    @Override
    public boolean isPathPresent() {
        return true;
    }

    @NotNull
    @Override
    public String getPath() {
        return fileObject.getName().getPath();
    }

    @NotNull
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
        return fileObject.getContent().getInputStream();
    }

    @NotNull
    @Override
    public OutputStream newOutputStream(OpenOption... options)
            throws IOException {
        boolean append = OpenOptions.isAppend(options);
        return fileObject.getContent().getOutputStream(append);
    }

    @NotNull
    @Override
    public IByteIn newByteIn(OpenOption... options)
            throws IOException {
        InputStream in = newInputStream(options);
        return new InputStreamByteIn(in);
    }

    @NotNull
    @Override
    public IByteOut newByteOut(OpenOption... options)
            throws IOException {
        OutputStream outputStream = newOutputStream(options);
        return new OutputStreamByteOut(outputStream);
    }

    @NotNull
    @Override
    public ICharIn newCharIn(OpenOption... options)
            throws IOException {
        Reader reader = newReader(options);
        return new ReaderCharIn(reader);
    }

    @NotNull
    @Override
    public ICharOut newCharOut(OpenOption... options)
            throws IOException {
        Writer writer = newWriter(options);
        return new WriterPrintOut(writer);
    }

}
