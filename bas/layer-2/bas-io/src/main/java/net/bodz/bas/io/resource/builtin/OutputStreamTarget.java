package net.bodz.bas.io.resource.builtin;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import net.bodz.bas.io.resource.AbstractStreamOutputTarget;
import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sio.OutputStreamByteOut;
import net.bodz.bas.sio.WriterPrintOut;

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
     * @param append
     *            Ignored. Append mode is meaningless for pure streaming.
     * @return {@link OutputStream} with {@link OutputStream#close()} filtered out.
     */
    @Override
    protected OutputStream _newOutputStream(boolean append)
            throws IOException {
        return new FilterOutputStream(out) {
            @Override
            public void close()
                    throws IOException {
                // Don't close the stream in the resource.
            }
        };
    }

    @Override
    protected Writer _newWriter(boolean append)
            throws IOException {
        return new OutputStreamWriter(_newOutputStream(append), getCharset());
    }

    @Override
    protected IByteOut _newByteOut(boolean append)
            throws IOException {
        return new OutputStreamByteOut(_newOutputStream(append));
    }

    @Override
    protected ICharOut _newCharOut(boolean append)
            throws IOException {
        return _newPrintOut(append);
    }

    @Override
    protected IPrintOut _newPrintOut(boolean append)
            throws IOException {
        return new WriterPrintOut(_newWriter(append));
    }

}
