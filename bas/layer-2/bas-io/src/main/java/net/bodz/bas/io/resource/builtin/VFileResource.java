package net.bodz.bas.io.resource.builtin;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.VFS;

import net.bodz.bas.io.resource.AbstractStreamResource;
import net.bodz.bas.sio.*;

public class VFileResource
        extends AbstractStreamResource {

    private final FileObject vfile;

    public VFileResource(FileObject vfile) {
        if (vfile == null)
            throw new NullPointerException("vfile");
        this.vfile = vfile;
    }

    public VFileResource(String vpath)
            throws FileSystemException {
        this(VFS.getManager().resolveFile(vpath));
    }

    public FileObject getVfile() {
        return vfile;
    }

    @Override
    protected InputStream _newInputStream()
            throws IOException {
        return vfile.getContent().getInputStream();
    }

    @Override
    protected OutputStream _newOutputStream(boolean append)
            throws IOException {
        return vfile.getContent().getOutputStream(append);
    }

    @Override
    protected IByteIn _newByteIn()
            throws IOException {
        return new InputStreamByteIn(_newInputStream());
    }

    @Override
    protected IByteOut _newByteOut(boolean append)
            throws IOException {
        return new OutputStreamByteOut(_newOutputStream(append));
    }

    @Override
    protected ICharIn _newCharIn()
            throws IOException {
        return new ReaderCharIn(newReader());
    }

    @Override
    protected IPrintOut _newCharOut(boolean append)
            throws IOException {
        return new WriterPrintOut(newWriter(append));
    }

}
