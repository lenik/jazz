package net.bodz.bas.io.resource.builtin;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import net.bodz.bas.io.WriterOutputStream;
import net.bodz.bas.io.resource.AbstractStreamOutputTarget;
import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.sio.OutputStreamByteOut;
import net.bodz.bas.sio.WriterCharOut;

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
    public Writer newWriter()
            throws IOException {
        return new FilterWriter(out) {
            @Override
            public void close()
                    throws IOException {
            }
        };
    }

    @Override
    public OutputStream newOutputStream()
            throws IOException {
        return new WriterOutputStream(newWriter(), getCharset());
    }

    @Override
    public IByteOut newByteOut()
            throws IOException {
        return new OutputStreamByteOut(newOutputStream());
    }

    @Override
    public ICharOut newCharOut()
            throws IOException {
        return new WriterCharOut(newWriter());
    }

}
