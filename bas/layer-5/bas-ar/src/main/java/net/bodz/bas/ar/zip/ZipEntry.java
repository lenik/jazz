package net.bodz.bas.ar.zip;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.OpenOption;
import java.util.zip.InflaterInputStream;

import net.bodz.bas.ar.AbstractArchiveEntry;
import net.bodz.bas.err.ReadOnlyException;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.adapter.InputStreamByteIn;
import net.bodz.bas.io.adapter.ReaderCharIn;
import net.bodz.bas.io.res.IStreamResource;

public class ZipEntry
        extends AbstractArchiveEntry
        implements IZipEntry {

    private final ZipUnarchiver ctx;

    String name;
    boolean directory;
    int time;
    int method;
    long compressedSize;
    long size;
    int crc32;
    byte[] extraBytes;
    long offset;

    public ZipEntry(ZipUnarchiver ctx) {
        if (ctx == null)
            throw new NullPointerException("ctx");
        this.ctx = ctx;
    }

    /** ⇱ Implementation Of {@link IZipEntry}. */
    ;

    @Override
    public int getMethod() {
        return method;
    }

    @Override
    public int getCrc32() {
        return crc32;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    /** ⇱ Implementation Of {@link IArchiveEntry}. */
    ;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isDirectory() {
        return directory;
    }

    @Override
    public long getModifiedTime() {
        return time;
    }

    @Override
    public long getArchivedSize() {
        return compressedSize;
    }

    @Override
    public long getSize() {
        return size;
    }

    /** ⇱ Implementation Of {@link StreamResourceTemplate}. */
    ;

    IStreamResource rawcrop()
            throws IOException {
        return ctx.seeker.crop(offset, offset + size);
    }

    @Override
    protected InputStream _newInputStream(OpenOption... options)
            throws IOException {
        IStreamResource crop = rawcrop();
        InputStream in = crop.newInputStream(options);

        switch (method) {
        case M_STORE:
            return in;
        case M_DEFLATE:
            return new InflaterInputStream(in);
        default:
            throw new UnsupportedOperationException("Unknown method: " + method);
        }
    }

    @Override
    protected IByteIn _newByteIn(OpenOption... options)
            throws IOException {
        IStreamResource crop = rawcrop();
        switch (method) {
        case M_STORE:
            return crop.newByteIn(options);

        case M_DEFLATE:
            InputStream in = crop.newInputStream(options);
            InputStream inflatedIn = new InflaterInputStream(in);
            return new InputStreamByteIn(inflatedIn);

        default:
            throw new UnsupportedOperationException("Unknown method: " + method);
        }
    }

    @Override
    protected Reader _newReader(OpenOption... options)
            throws IOException {
        InputStream in = _newInputStream(options);
        return new InputStreamReader(in, getCharset());
    }

    @Override
    protected ICharIn _newCharIn(OpenOption... options)
            throws IOException {
        Reader reader = _newReader(options);
        return new ReaderCharIn(reader);
    }

    @Override
    protected IByteOut _newByteOut(OpenOption... options)
            throws IOException {
        throw new ReadOnlyException();
    }

    @Override
    protected ICharOut _newCharOut(OpenOption... options)
            throws IOException {
        throw new ReadOnlyException();
    }

}
