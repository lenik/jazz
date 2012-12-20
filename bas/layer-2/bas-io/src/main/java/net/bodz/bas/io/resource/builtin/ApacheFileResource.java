package net.bodz.bas.io.resource.builtin;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.OpenOption;

import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.VFS;

import net.bodz.bas.c.java.nio.CommonOpenConfig;
import net.bodz.bas.io.resource.AbstractStreamResource;
import net.bodz.bas.sio.*;

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
        CommonOpenConfig config = CommonOpenConfig.parse(options);
        return fileObject.getContent().getOutputStream(config.isAppend());
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
