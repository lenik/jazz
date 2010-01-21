package net.bodz.bas.io.resource.builtin;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import net.bodz.bas.io.resource.AbstractStreamOutputTarget;
import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.sio.OutputStreamByteOut;
import net.bodz.bas.sio.WriterCharOut;

public class OutputStreamTarget
        extends AbstractStreamOutputTarget {

    private final OutputStream out;

    /**
     * @throws NullPointerException
     *             If <code>out</code> is <code>null</code>.
     */
    public OutputStreamTarget(OutputStream out) {
        if (out == null)
            throw new NullPointerException("out");
        this.out = out;
    }

    /**
     * @return {@link OutputStream} with {@link OutputStream#close()} filtered out.
     */
    @Override
    public OutputStream newOutputStream()
            throws IOException {
        return new FilterOutputStream(out) {
            @Override
            public void close()
                    throws IOException {
            }
        };
    }

    @Override
    public Writer newWriter()
            throws IOException {
        return new OutputStreamWriter(newOutputStream(), getCharset());
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
