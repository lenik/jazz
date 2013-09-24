package net.bodz.bas.ar.zip;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.OpenOption;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

import net.bodz.bas.c.java.io.DbgInputStream;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.adapter.InputStreamByteIn;
import net.bodz.bas.io.res.AbstractInputStreamSource;
import net.bodz.bas.io.res.IStreamResource;

public class ZipEntrySource
        extends AbstractInputStreamSource
        implements IZipConsts {

    private final IZipContext ctx;
    private final ZipEntry entry;

    public ZipEntrySource(IZipContext ctx, ZipEntry entry) {
        if (ctx == null)
            throw new NullPointerException("ctx");
        if (entry == null)
            throw new NullPointerException("entry");
        this.ctx = ctx;
        this.entry = entry;
    }

    /** ⇱ Implementation Of {@link IStreamInputSource}. */
    ;

    IStreamResource rawcrop()
            throws IOException {
        if (entry.dataAddress == -1L) {
            ctx.reloadLFH(entry);
        }

        long start = entry.dataAddress;
        long end = start + entry.compressedSize;
        return ctx.crop(start, end);
    }

    @Override
    protected InputStream _newInputStream(OpenOption... options)
            throws IOException {
        IStreamResource src = rawcrop();
        InputStream in = src.newInputStream(options);
        in = new DbgInputStream(in);

        switch (entry.method) {
        case M_STORE:
            return in;

        case M_DEFLATE:
            return new InflaterInputStream(in, new Inflater(true));

        default:
            throw new UnsupportedOperationException("Unknown method: " + entry.method);
        }
    }

    @Override
    protected IByteIn _newByteIn(OpenOption... options)
            throws IOException {
        IStreamResource src = rawcrop();

        switch (entry.method) {
        case M_STORE:
            return src.newByteIn(options);

        case M_DEFLATE:
            InputStream deflated = src.newInputStream(options);
            deflated = new DbgInputStream(deflated);
            InflaterInputStream in = new InflaterInputStream(deflated, new Inflater(true));
            return new InputStreamByteIn(in);

        default:
            throw new UnsupportedOperationException("Unknown method: " + entry.method);
        }
    }

}
