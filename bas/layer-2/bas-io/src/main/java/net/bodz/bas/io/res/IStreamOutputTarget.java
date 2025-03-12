package net.bodz.bas.io.res;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.nio.file.OpenOption;

import net.bodz.bas.c.java.io.DataOutputAdapter;
import net.bodz.bas.c.java.io.IDataOutput;
import net.bodz.bas.c.java.io.IObjectOutput;
import net.bodz.bas.c.java.io.ObjectOutputAdapter;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.IDataOut;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.adapter.ByteOutOutputStream;
import net.bodz.bas.io.adapter.CharOutWriter;
import net.bodz.bas.io.data.DataOutImplBE;
import net.bodz.bas.io.data.DataOutImplLE;
import net.bodz.bas.io.impl.PrintOutImpl;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.bas.io.res.tools.IStreamWriting;
import net.bodz.bas.io.res.tools.StreamWriting;
import net.bodz.bas.meta.decl.DefaultImpl;
import net.bodz.bas.meta.decl.NotNull;

public interface IStreamOutputTarget
        extends IResourceEntry {

    @Override
    default boolean canRead() {
        return false;
    }

    /**
     * @return non-<code>null</code> value.
     */
    @NotNull
    IByteOut newByteOut(OpenOption... options)
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    @NotNull
    ICharOut newCharOut(OpenOption... options)
            throws IOException;

    @DefaultImpl(PrintOutImpl.class)
    @NotNull
    default IPrintOut newPrintOut(OpenOption... options)
            throws IOException {
        ICharOut charOut = newCharOut(options);
        return PrintOutImpl.from(charOut);
    }

    @DefaultImpl(DataOutImplLE.class)
    @NotNull
    default IDataOut newDataOutLE(OpenOption... options)
            throws IOException {
        IByteOut byteOut = newByteOut(options);
        return DataOutImplLE.from(byteOut);
    }

    @DefaultImpl(DataOutImplBE.class)
    @NotNull
    default IDataOut newDataOutBE(OpenOption... options)
            throws IOException {
        IByteOut byteOut = newByteOut(options);
        return DataOutImplBE.from(byteOut);
    }

    @DefaultImpl(ByteOutOutputStream.class)
    @NotNull
    default OutputStream newOutputStream(OpenOption... options)
            throws IOException {
        IByteOut byteOut = newByteOut(options);
        return new ByteOutOutputStream(byteOut);
    }

    @DefaultImpl(DataOutputAdapter.class)
    @NotNull
    default IDataOutput newDataOutput(OpenOption... options)
            throws IOException {
        OutputStream outputStream = newOutputStream(options);
        if (outputStream instanceof IDataOutput)
            return (IDataOutput) outputStream;

        DataOutputStream dos;
        if (outputStream instanceof DataOutputStream)
            dos = (DataOutputStream) outputStream;
        else
            dos = new DataOutputStream(outputStream);

        IDataOutput dataOut = new DataOutputAdapter(dos);
        return dataOut;
    }

    @DefaultImpl(ObjectOutputStream.class)
    @NotNull
    default IObjectOutput newObjectOutput(OpenOption... options)
            throws IOException {
        OutputStream outputStream = newOutputStream(options);

        if (outputStream instanceof IObjectOutput)
            return (IObjectOutput) outputStream;

        ObjectOutputStream oos;
        if (outputStream instanceof ObjectOutputStream)
            oos = (ObjectOutputStream) outputStream;
        else
            oos = new ObjectOutputStream(outputStream);

        return new ObjectOutputAdapter(oos);
    }

    @DefaultImpl(PrintStream.class)
    @NotNull
    default PrintStream newPrintStream(OpenOption... options)
            throws IOException {
        OutputStream outputStream = newOutputStream(options);
        if (outputStream instanceof PrintStream)
            return (PrintStream) outputStream;
        return new PrintStream(outputStream);
    }

    @DefaultImpl(CharOutWriter.class)
    @NotNull
    default Writer newWriter(OpenOption... options)
            throws IOException {
        ICharOut charOut = newCharOut(options);
        return new CharOutWriter(charOut);
    }

    @DefaultImpl(BufferedWriter.class)
    @NotNull
    default BufferedWriter newBufferedWriter(OpenOption... options)
            throws IOException {
        Writer writer = newWriter(options);
        return new BufferedWriter(writer);
    }

    @NotNull
    default ITreeOut newTreeOut(OpenOption... options)
            throws IOException {
        ICharOut charOut = newCharOut(options);
        return TreeOutImpl.from(charOut);
    }

    @NotNull
    default IStreamWriting write() {
        return new StreamWriting(this);
    }

}
