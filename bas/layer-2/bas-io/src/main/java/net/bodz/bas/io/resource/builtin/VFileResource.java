package net.bodz.bas.io.resource.builtin;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.VFS;

import net.bodz.bas.io.resource.AbstractStreamResource;
import net.bodz.bas.sio.IByteIn;
import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.ICharIn;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sio.InputStreamByteIn;
import net.bodz.bas.sio.OutputStreamByteOut;
import net.bodz.bas.sio.ReaderCharIn;
import net.bodz.bas.sio.WriterPrintOut;

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
    public InputStream newInputStream()
            throws IOException {
        return vfile.getContent().getInputStream();
    }

    @Override
    public OutputStream newOutputStream()
            throws IOException {
        return vfile.getContent().getOutputStream(isAppendMode());
    }

    @Override
    public IByteIn newByteIn()
            throws IOException {
        return new InputStreamByteIn(newInputStream());
    }

    @Override
    public IByteOut newByteOut()
            throws IOException {
        return new OutputStreamByteOut(newOutputStream());
    }

    @Override
    public ICharIn newCharIn()
            throws IOException {
        return new ReaderCharIn(newReader());
    }

    @Override
    public IPrintOut newCharOut()
            throws IOException {
        return new WriterPrintOut(newWriter());
    }

}
