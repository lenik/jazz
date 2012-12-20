package net.bodz.bas.io.resource.builtin;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.file.OpenOption;

import net.bodz.bas.io.WriterOutputStream;
import net.bodz.bas.io.resource.AbstractStreamOutputTarget;
import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sio.OutputStreamByteOut;
import net.bodz.bas.sio.WriterPrintOut;

public class WriterTarget
        extends AbstractStreamOutputTarget {

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
    public Writer _newWriter(OpenOption... options)
            throws IOException {
        return new FilterWriter(out) {
            @Override
            public void close()
                    throws IOException {
            }
        };
    }

    @Override
    protected OutputStream _newOutputStream(OpenOption... options)
            throws IOException {
        Writer writer = _newWriter(options);
        return new WriterOutputStream(writer, getCharset());
    }

    @Override
    protected IByteOut _newByteOut(OpenOption... options)
            throws IOException {
        OutputStream outputStream = _newOutputStream(options);
        return new OutputStreamByteOut(outputStream);
    }

    @Override
    protected ICharOut _newCharOut(OpenOption... options)
            throws IOException {
        return _newPrintOut(options);
    }

    @Override
    protected IPrintOut _newPrintOut(OpenOption... options)
            throws IOException {
        Writer writer = _newWriter(options);
        return new WriterPrintOut(writer);
    }

}
