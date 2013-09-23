package net.bodz.bas.c.java.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.OpenOption;

import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.res.AbstractInputOutputStreamResource;

public class CroppedRafStreamResource
        extends AbstractInputOutputStreamResource {

    private File file;
    private String mode;
    private long start;
    private long end;

    public CroppedRafStreamResource(File file, String mode, long start, long end) {
        this.file = file;
        this.mode = mode;
        this.start = start;
        this.end = end;
    }

    public CroppedRafStreamResource(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected InputStream _newInputStream(OpenOption... options)
            throws IOException {
        return new CroppedRafInputStream(file, mode, start, end);
    }

    @Override
    protected IByteIn _newByteIn(OpenOption... options)
            throws IOException {
        return new CroppedRafInputStream(file, mode, start, end);
    }

    @Override
    protected OutputStream _newOutputStream(OpenOption... options)
            throws IOException {
        return new CroppedRafOutputStream(file, mode, start, end);
    }

    @Override
    protected IByteOut _newByteOut(OpenOption... options)
            throws IOException {
        return new CroppedRafOutputStream(file, mode, start, end);
    }

}
