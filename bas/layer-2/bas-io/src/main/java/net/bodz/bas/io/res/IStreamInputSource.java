package net.bodz.bas.io.res;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Reader;
import java.nio.file.OpenOption;

import net.bodz.bas.c.java.io.DataInputAdapter;
import net.bodz.bas.c.java.io.IDataInput;
import net.bodz.bas.c.java.io.IObjectInput;
import net.bodz.bas.c.java.io.LineReader;
import net.bodz.bas.c.java.io.ObjectInputAdapter;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.adapter.ByteInInputStream;
import net.bodz.bas.io.adapter.CharInReader;
import net.bodz.bas.io.bit.BitInImpl;
import net.bodz.bas.io.bit.IBitIn;
import net.bodz.bas.io.data.DataInImplBE;
import net.bodz.bas.io.data.DataInImplLE;
import net.bodz.bas.io.impl.LAReader;
import net.bodz.bas.io.res.tools.IStreamReading;
import net.bodz.bas.io.res.tools.StreamReading;
import net.bodz.bas.meta.decl.DefaultImpl;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.sugar.IToChain;

public interface IStreamInputSource
        extends IResourceEntry {

    @Override
    default boolean canWrite() {
        return false;
    }

    @NotNull
    IByteIn newByteIn(OpenOption... options)
            throws IOException;

    @NotNull
    ICharIn newCharIn(OpenOption... options)
            throws IOException;

    @DefaultImpl(ByteInInputStream.class)
    @NotNull
    default InputStream newInputStream(OpenOption... options)
            throws IOException {
        IByteIn byteIn = newByteIn(options);
        return new ByteInInputStream(byteIn);
    }

    @DefaultImpl(DataInputStream.class)
    @NotNull
    default IDataInput newDataInput(OpenOption... options)
            throws IOException {
        InputStream inputStream = newInputStream(options);

        DataInputStream dataIn;
        if (inputStream instanceof DataInputStream)
            dataIn = (DataInputStream) inputStream;
        else
            dataIn = new DataInputStream(inputStream);

        return new DataInputAdapter(dataIn);
    }

    @DefaultImpl(ObjectInputStream.class)
    @NotNull
    default IObjectInput newObjectInput(OpenOption... options)
            throws IOException {
        InputStream inputStream = newInputStream(options);
        if (inputStream == null)
            throw new NullPointerException("inputStream");
        if (inputStream instanceof IObjectInput)
            return (IObjectInput) inputStream;
        ObjectInputStream in = new ObjectInputStream(inputStream);
        return new ObjectInputAdapter(in, in);
    }

    @NotNull
    @DefaultImpl(CharInReader.class)
    default Reader newReader(OpenOption... options)
            throws IOException {
        ICharIn charIn = newCharIn(options);
        return new CharInReader(charIn);
    }

    @DefaultImpl(LAReader.class)
    @NotNull
    default LAReader newLAReader(OpenOption... options)
            throws IOException {
        Reader reader = newReader(options);
        if (reader instanceof LAReader)
            return (LAReader) reader;
        return new LAReader(reader);
    }

    @DefaultImpl(BufferedReader.class)
    @NotNull
    default BufferedReader newBufferedReader(OpenOption... options)
            throws IOException {
        Reader reader = newReader(options);
        if (reader instanceof BufferedReader)
            return (BufferedReader) reader;
        return new BufferedReader(reader);
    }

    @DefaultImpl(LineReader.class)
    @NotNull
    default LineReader newLineReader(OpenOption... options)
            throws IOException {
        Reader reader = newReader(options);
        if (reader instanceof LineReader)
            return (LineReader) reader;
        return new LineReader(reader);
    }

    @NotNull
    @DefaultImpl(BitInImpl.class)
    default IBitIn newBitIn(OpenOption... options)
            throws IOException {
        IByteIn byteIn = newByteIn(options);
        return BitInImpl.from(byteIn);
    }

    @DefaultImpl(DataInImplLE.class)
    default IDataIn newDataInLE(OpenOption... options)
            throws IOException {
        IByteIn byteIn = newByteIn(options);
        return DataInImplLE.from(byteIn);
    }

    @DefaultImpl(DataInImplBE.class)
    default IDataIn newDataInBE(OpenOption... options)
            throws IOException {
        IByteIn byteIn = newByteIn(options);
        return DataInImplBE.from(byteIn);
    }

    default IStreamReading read() {
        return new StreamReading(this);
    }

}
