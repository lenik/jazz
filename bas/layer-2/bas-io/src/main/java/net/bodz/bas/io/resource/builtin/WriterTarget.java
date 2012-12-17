package net.bodz.bas.io.resource.builtin;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

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
     * @return {@link Writer} with {@link Writer#close()} filtered out.
     */
    @Override
    public Writer _newWriter()
            throws IOException {
        return new FilterWriter(out) {
            @Override
            public void close()
                    throws IOException {
            }
        };
    }

    @Override
    protected OutputStream _newOutputStream()
            throws IOException {
        return new WriterOutputStream(_newWriter(), getCharset());
    }

    @Override
    protected IByteOut _newByteOut()
            throws IOException {
        return new OutputStreamByteOut(_newOutputStream());
    }

    @Override
    protected ICharOut _newCharOut()
            throws IOException {
        return _newPrintOut();
    }

    @Override
    protected IPrintOut _newPrintOut()
            throws IOException {
        return new WriterPrintOut(_newWriter());
    }

}
