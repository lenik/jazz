package net.bodz.bas.ar.zip;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.OpenOption;
import java.util.zip.InflaterInputStream;

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
        return ctx.crop(entry.dataAddress, entry.dataAddress + entry.size);
    }

    @Override
    protected InputStream _newInputStream(OpenOption... options)
            throws IOException {
        IStreamResource crop = rawcrop();
        InputStream in = crop.newInputStream(options);

        switch (entry.method) {
        case M_STORE:
            return in;
        case M_DEFLATE:
            return new InflaterInputStream(in);
        default:
            throw new UnsupportedOperationException("Unknown method: " + entry.method);
        }
    }

    @Override
    protected IByteIn _newByteIn(OpenOption... options)
            throws IOException {
        IStreamResource crop = rawcrop();
        switch (entry.method) {
        case M_STORE:
            return crop.newByteIn(options);

        case M_DEFLATE:
            InputStream in = crop.newInputStream(options);
            InputStream inflatedIn = new InflaterInputStream(in);
            return new InputStreamByteIn(inflatedIn);

        default:
            throw new UnsupportedOperationException("Unknown method: " + entry.method);
        }
    }

}
