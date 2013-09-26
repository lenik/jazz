package net.bodz.bas.io.res.builtin;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.OpenOption;

import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.VFS;

import net.bodz.bas.c.java.nio.OpenOptions;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.adapter.InputStreamByteIn;
import net.bodz.bas.io.adapter.OutputStreamByteOut;
import net.bodz.bas.io.adapter.ReaderCharIn;
import net.bodz.bas.io.adapter.WriterPrintOut;
import net.bodz.bas.io.res.AbstractStreamResource;

public class ApacheFileResource
        extends AbstractStreamResource {

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
    protected InputStream _newInputStream(OpenOption... options)
            throws IOException {
        return fileObject.getContent().getInputStream();
    }

    @Override
    protected OutputStream _newOutputStream(OpenOption... options)
            throws IOException {
        boolean append = OpenOptions.isAppend(options);
        return fileObject.getContent().getOutputStream(append);
    }

    @Override
    protected IByteIn _newByteIn(OpenOption... options)
            throws IOException {
        InputStream in = _newInputStream(options);
        return new InputStreamByteIn(in);
    }

    @Override
    protected IByteOut _newByteOut(OpenOption... options)
            throws IOException {
        OutputStream outputStream = _newOutputStream(options);
        return new OutputStreamByteOut(outputStream);
    }

    @Override
    protected ICharIn _newCharIn(OpenOption... options)
            throws IOException {
        Reader reader = _newReader(options);
        return new ReaderCharIn(reader);
    }

    @Override
    protected IPrintOut _newCharOut(OpenOption... options)
            throws IOException {
        Writer writer = _newWriter(options);
        return new WriterPrintOut(writer);
    }

}
