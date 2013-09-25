package net.bodz.bas.ar.zip;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.OpenOption;
import java.util.zip.Inflater;
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
        if (entry.dataAddress == -1L)
            ctx.reloadLFH(entry);

        long rawsize = entry.compressedSize;
        if (entry.isEncrytped())
            rawsize -= 12;

        long start = entry.dataAddress;
        long end = start + rawsize;

        long zipLength = ctx.getZipLength();
        if (end > zipLength)
            throw new IOException(String.format("Entry %s exceeds the zip file, maybe it's incompleted.",
                    entry.getName()));

        return ctx.crop(start, end);
    }

    InputStream decrypt(InputStream in) {
        if (!entry.isEncrytped())
            return in;
        ZipEncryptKey key = entry.getEncryptKey();
        return new DecryptedInputStream(in, key);
    }

    IByteIn decrypt(IByteIn in) {
        if (!entry.isEncrytped())
            return in;
        ZipEncryptKey key = entry.getEncryptKey();
        return new DecryptedByteIn(in, key);
    }

    @Override
    protected InputStream _newInputStream(OpenOption... options)
            throws IOException {
        IStreamResource src = rawcrop();
        InputStream in = src.newInputStream(options);
        in = decrypt(in);

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
            return decrypt(src.newByteIn(options));

        case M_DEFLATE:
            InputStream in = src.newInputStream(options);
            in = decrypt(in);
            InflaterInputStream inflated = new InflaterInputStream(in, new Inflater(true));
            return new InputStreamByteIn(inflated);

        default:
            throw new UnsupportedOperationException("Unknown method: " + entry.method);
        }
    }

}
