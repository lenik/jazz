package net.bodz.bas.io.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.OpenOption;

import net.bodz.bas.io.IByteIOS;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.res.AbstractIOStreamResource;

public class CroppedRafResource
        extends AbstractIOStreamResource<CroppedRafResource> {

    private File file;
    private String mode;
    private long start;
    private long end;

    public CroppedRafResource(File file, String mode, long start, long end) {
        this.file = file;
        this.mode = mode;
        this.start = start;
        this.end = end;
    }

    public CroppedRafResource(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public InputStream newInputStream(OpenOption... options)
            throws IOException {
        return new CroppedRafIn(file, mode, start, end);
    }

    @Override
    public IByteIn newByteIn(OpenOption... options)
            throws IOException {
        return new CroppedRafIn(file, mode, start, end);
    }

    @Override
    public OutputStream newOutputStream(OpenOption... options)
            throws IOException {
        return new CroppedRafOut(file, mode, start, end);
    }

    @Override
    public IByteOut newByteOut(OpenOption... options)
            throws IOException {
        return new CroppedRafOut(file, mode, start, end);
    }

    @Override
    public IByteIOS newByteIOS(OpenOption... options)
            throws IOException {
        return new CroppedRafIOS(file, mode, start, end);
    }

}
