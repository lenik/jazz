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
import net.bodz.bas.meta.decl.DefaultImpl;

public abstract class AbstractIORandomResource<This>
        extends AbstractRandomResource<This> {

    /** ⇱ Implementaton Of {@link IStreamInputSource}. */
    /* _____________________________ */static section.iface __IN__;

    @Override
    public abstract InputStream newInputStream(OpenOption... options)
            throws IOException;

    @DefaultImpl(InputStreamReader.class)
    @Override
    public final Reader newReader(OpenOption... options)
            throws IOException {
        InputStream in = newInputStream(options);
        return new InputStreamReader(in, getCharset());
    }

    @DefaultImpl(InputStreamByteIn.class)
    @Override
    public IByteIn newByteIn(OpenOption... options)
            throws IOException {
        InputStream in = newInputStream(options);
        // if (in instanceof IByteIn) return (IByteIn) in;
        return new InputStreamByteIn(in);
    }

    @DefaultImpl(DecodedCharIn.class)
    @Override
    public final ICharIn newCharIn(OpenOption... options)
            throws IOException {
        IByteIn in = newByteIn(options);
        return new DecodedCharIn(in, getCharset().newDecoder());
    }

    /** ⇱ Implementaton Of {@link IStreamOutputTarget}. */
    /* _____________________________ */static section.iface __OUT__;

    @Override
    public abstract OutputStream newOutputStream(OpenOption... options)
            throws IOException;

    @DefaultImpl(OutputStreamWriter.class)
    @Override
    public Writer newWriter(OpenOption... options)
            throws IOException {
        OutputStream out = newOutputStream(options);
        return new OutputStreamWriter(out, getCharset());
    }

    @DefaultImpl(OutputStreamByteOut.class)
    @Override
    public IByteOut newByteOut(OpenOption... options)
            throws IOException {
        OutputStream out = newOutputStream(options);
//        if (out instanceof IByteOut)
//            return (IByteOut) out;
        return new OutputStreamByteOut(out);
    }

    @DefaultImpl(PrintStreamPrintOut.class)
    @Override
    public final IPrintOut newPrintOut(OpenOption... options)
            throws IOException {
        PrintStream ps = newPrintStream(options);
//        if (ps instanceof IPrintOut)
//            return (IPrintOut) ps;
        return new PrintStreamPrintOut(ps);
    }

    @DefaultImpl(WriterCharOut.class)
    @Override
    public final ICharOut newCharOut(OpenOption... options)
            throws IOException {
        Writer writer = newWriter(options);
//        if (writer instanceof ICharOut)
//            return (ICharOut) writer;
        return new WriterCharOut(writer);
    }

}
