package net.bodz.bas.io.res;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.OpenOption;

import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.adapter.InputStreamByteIn;
import net.bodz.bas.io.adapter.OutputStreamByteOut;
import net.bodz.bas.io.adapter.PrintStreamPrintOut;
import net.bodz.bas.io.adapter.WriterCharOut;
import net.bodz.bas.io.impl.DecodedCharIn;
import net.bodz.bas.meta.decl.NotNull;

public abstract class AbstractIOStreamResource<This>
        extends AbstractStreamResource<This> {

    /** ⇱ Implementaton Of {@link IStreamInputSource}. */
    /* _____________________________ */static section.iface __IN__;

    @NotNull
    @Override
    public abstract InputStream newInputStream(OpenOption... options)
            throws IOException;

    @NotNull
    @Override
    public final Reader newReader(OpenOption... options)
            throws IOException {
        InputStream in = newInputStream(options);
        return new InputStreamReader(in, getCharset());
    }

    @NotNull
    @Override
    public IByteIn newByteIn(OpenOption... options)
            throws IOException {
        InputStream in = newInputStream(options);
        // if (in instanceof IByteIn) return (IByteIn) in;
        return new InputStreamByteIn(in);
    }

    @NotNull
    @Override
    public final ICharIn newCharIn(OpenOption... options)
            throws IOException {
        IByteIn in = newByteIn(options);
        return new DecodedCharIn(in, getCharset().newDecoder());
    }

    /** ⇱ Implementaton Of {@link IStreamOutputTarget}. */
    /* _____________________________ */static section.iface __OUT__;

    @NotNull
    @Override
    public abstract OutputStream newOutputStream(OpenOption... options)
            throws IOException;

    @NotNull
    @Override
    public final Writer newWriter(OpenOption... options)
            throws IOException {
        OutputStream out = newOutputStream(options);
        return new OutputStreamWriter(out, getCharset());
    }

    @NotNull
    @Override
    public IByteOut newByteOut(OpenOption... options)
            throws IOException {
        OutputStream out = newOutputStream(options);
        // if (out instanceof IByteOut) return (IByteOut) out;
        return new OutputStreamByteOut(out);
    }

    @NotNull
    @Override
    public final IPrintOut newPrintOut(OpenOption... options)
            throws IOException {
        PrintStream ps = newPrintStream(options);
        return new PrintStreamPrintOut(ps);
    }

    @NotNull
    @Override
    public final ICharOut newCharOut(OpenOption... options)
            throws IOException {
        Writer writer = newWriter(options);
        return new WriterCharOut(writer);
    }

}
