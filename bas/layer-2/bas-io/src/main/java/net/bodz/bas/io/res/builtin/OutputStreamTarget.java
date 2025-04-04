package net.bodz.bas.io.res.builtin;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.OpenOption;

import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.adapter.OutputStreamByteOut;
import net.bodz.bas.io.adapter.WriterPrintOut;
import net.bodz.bas.io.res.AbstractStreamOutputTarget;
import net.bodz.bas.meta.decl.NotNull;

public class OutputStreamTarget
        extends AbstractStreamOutputTarget<OutputStreamTarget> {

    @NotNull
    private final OutputStream out;

    public OutputStreamTarget(@NotNull  OutputStream out) {
        this.out = out;
    }

    @NotNull
    public OutputStream getOutputStream() {
        return out;
    }

    /**
     * @param append
     *            Ignored. Append mode is meaningless for pure streaming.
     * @return {@link OutputStream} with {@link OutputStream#close()} filtered out.
     */
    @NotNull
    @Override
    public OutputStream newOutputStream(OpenOption... options)
            throws IOException {
        return new FilterOutputStream(out) {
            @Override
            public void close()
                    throws IOException {
                // Don't close the stream in the resource.
            }
        };
    }

    @NotNull
    @Override
    public Writer newWriter(OpenOption... options)
            throws IOException {
        OutputStream outputStream = newOutputStream(options);
        return new OutputStreamWriter(outputStream, getCharset());
    }

    @NotNull
    @Override
    public IByteOut newByteOut(OpenOption... options)
            throws IOException {
        OutputStream outputStream = newOutputStream(options);
        return new OutputStreamByteOut(outputStream);
    }

    @NotNull
    @Override
    public ICharOut newCharOut(OpenOption... options)
            throws IOException {
        return newPrintOut(options);
    }

    @NotNull
    @Override
    public IPrintOut newPrintOut(OpenOption... options)
            throws IOException {
        Writer writer = newWriter(options);
        return new WriterPrintOut(writer);
    }

}
