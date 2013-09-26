package net.bodz.bas.io.res;

import java.io.*;
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

public abstract class AbstractInputOutputStreamResource
        extends AbstractStreamResource {

    /** ⇱ Implementaton Of {@link IStreamInputSource}. */
    /* _____________________________ */static section.iface __IN__;

    @Override
    protected abstract InputStream _newInputStream(OpenOption... options)
            throws IOException;

    @Override
    protected final Reader _newReader(OpenOption... options)
            throws IOException {
        InputStream in = _newInputStream(options);
        return new InputStreamReader(in, getCharset());
    }

    @Override
    protected IByteIn _newByteIn(OpenOption... options)
            throws IOException {
        InputStream in = _newInputStream(options);
        // if (in instanceof IByteIn) return (IByteIn) in;
        return new InputStreamByteIn(in);
    }

    @Override
    protected final ICharIn _newCharIn(OpenOption... options)
            throws IOException {
        IByteIn in = _newByteIn(options);
        return new DecodedCharIn(in, getCharset().newDecoder());
    }

    /** ⇱ Implementaton Of {@link IStreamOutputTarget}. */
    /* _____________________________ */static section.iface __OUT__;

    @Override
    protected abstract OutputStream _newOutputStream(OpenOption... options)
            throws IOException;

    @Override
    protected final Writer _newWriter(OpenOption... options)
            throws IOException {
        OutputStream out = _newOutputStream(options);
        return new OutputStreamWriter(out, getCharset());
    }

    @Override
    protected IByteOut _newByteOut(OpenOption... options)
            throws IOException {
        OutputStream out = _newOutputStream(options);
        // if (out instanceof IByteOut) return (IByteOut) out;
        return new OutputStreamByteOut(out);
    }

    @Override
    protected final IPrintOut _newPrintOut(OpenOption... options)
            throws IOException {
        PrintStream ps = _newPrintStream(options);
        return new PrintStreamPrintOut(ps);
    }

    @Override
    protected final ICharOut _newCharOut(OpenOption... options)
            throws IOException {
        Writer writer = _newWriter(options);
        return new WriterCharOut(writer);
    }

}
