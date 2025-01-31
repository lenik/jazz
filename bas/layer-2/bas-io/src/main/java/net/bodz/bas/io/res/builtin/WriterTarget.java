package net.bodz.bas.io.res.builtin;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.file.OpenOption;

import net.bodz.bas.c.java.io.WriterOutputStream;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.adapter.OutputStreamByteOut;
import net.bodz.bas.io.adapter.WriterPrintOut;
import net.bodz.bas.io.res.AbstractStreamOutputTarget;

public class WriterTarget
        extends AbstractStreamOutputTarget<WriterTarget> {

    private final Writer out;

    /**
     * @throws NullPointerException
     *             If <code>out</code> is <code>null</code>.
     */
    public WriterTarget(Writer out) {
        if (out == null)
            throw new NullPointerException("out");
        this.out = out;
    }

    /**
     * @param append
     *            Ignored. Append mode is meaningless for pure streaming.
     * @return {@link Writer} with {@link Writer#close()} filtered out.
     */
    @Override
    public Writer newWriter(OpenOption... options)
            throws IOException {
        return new FilterWriter(out) {
            @Override
            public void close()
                    throws IOException {
            }
        };
    }

    @Override
    public OutputStream newOutputStream(OpenOption... options)
            throws IOException {
        Writer writer = newWriter(options);
        return new WriterOutputStream(writer, getCharset());
    }

    @Override
    public IByteOut newByteOut(OpenOption... options)
            throws IOException {
        OutputStream outputStream = newOutputStream(options);
        return new OutputStreamByteOut(outputStream);
    }

    @Override
    public ICharOut newCharOut(OpenOption... options)
            throws IOException {
        return newPrintOut(options);
    }

    @Override
    public IPrintOut newPrintOut(OpenOption... options)
            throws IOException {
        Writer writer = newWriter(options);
        return new WriterPrintOut(writer);
    }

}
