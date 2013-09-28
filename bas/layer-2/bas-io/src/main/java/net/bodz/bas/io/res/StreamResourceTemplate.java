package net.bodz.bas.io.res;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.java.io.DataOutputAdapter;
import net.bodz.bas.c.java.io.IDataOutput;
import net.bodz.bas.c.java.io.IObjectOutput;
import net.bodz.bas.c.java.io.LineReader;
import net.bodz.bas.c.java.io.ObjectOutputAdapter;
import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.io.*;
import net.bodz.bas.io.adapter.ByteInInputStream;
import net.bodz.bas.io.adapter.ByteOutOutputStream;
import net.bodz.bas.io.adapter.CharInReader;
import net.bodz.bas.io.adapter.CharOutWriter;
import net.bodz.bas.io.data.DataOutImplBE;
import net.bodz.bas.io.data.DataOutImplLE;
import net.bodz.bas.io.impl.LAReader;
import net.bodz.bas.io.impl.PrintOutImpl;
import net.bodz.bas.sugar.Tooling;

public abstract class StreamResourceTemplate {

    private Charset charset;
    private List<IOpenResourceListener> openResourceListeners = new ArrayList<IOpenResourceListener>(1);

    public StreamResourceTemplate() {
        charset = getPreferredCharset();
        if (charset == null)
            throw new NullPointerException("preferredCharset");
    }

    public <T> T to(Class<T> anotherType) {
        return new Tooling(this).to(anotherType);
    }

    /**
     * @return Preferred non-<code>null</code> {@link Charset}, the default implementation returns
     *         UTF-8 charset.
     */
    private Charset getPreferredCharset() {
        return Charsets.UTF8;
    }

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        if (charset == null)
            throw new NullPointerException("charset");
        this.charset = charset;
    }

    public void setCharset(String charsetName) {
        if (charsetName == null)
            throw new NullPointerException("charsetName");
        this.charset = Charset.forName(charsetName);
    }

    public void addOpenResourceListener(IOpenResourceListener listener) {
        if (listener == null)
            throw new NullPointerException("listener");
        openResourceListeners.add(listener);
    }

    public void removeOpenResourceListener(IOpenResourceListener listener) {
        if (listener == null)
            throw new NullPointerException("listener");
        openResourceListeners.remove(listener);
    }

    protected void fireOpenResource(boolean output, OpenOption... options)
            throws IOException {
        OpenResourceEvent event = new OpenResourceEvent(this, output, options);
        for (IOpenResourceListener listener : openResourceListeners)
            listener.openResource(event);
    }

    protected void beforeOpenInput(OpenOption... options)
            throws IOException {
        fireOpenResource(false, options);
    }

    protected void beforeOpenOutput(OpenOption... options)
            throws IOException {
        fireOpenResource(true, options);
    }

    protected void afterOpenInput(AutoCloseable in)
            throws IOException {
    }

    protected void afterOpenOutput(AutoCloseable out)
            throws IOException {
    }

    // input

    // IByteIn

    protected abstract IByteIn _newByteIn(OpenOption... options)
            throws IOException;

    public final IByteIn newByteIn(OpenOption... options)
            throws IOException {
        beforeOpenInput(options);
        IByteIn in = _newByteIn(options);
        afterOpenInput(in);
        return in;
    }

    // ICharIn

    protected abstract ICharIn _newCharIn(OpenOption... options)
            throws IOException;

    public final ICharIn newCharIn(OpenOption... options)
            throws IOException {
        beforeOpenInput(options);
        ICharIn in = _newCharIn(options);
        afterOpenInput(in);
        return in;
    }

    // InputStream

    /**
     * @def new ByteInInputStream(newByteIn())
     */
    protected InputStream _newInputStream(OpenOption... options)
            throws IOException {
        IByteIn byteIn = newByteIn(options);
        if (byteIn == null)
            throw new NullPointerException("byteIn");
        return new ByteInInputStream(byteIn);
    }

    public final InputStream newInputStream(OpenOption... options)
            throws IOException {
        beforeOpenInput(options);
        InputStream in = _newInputStream(options);
        afterOpenInput(in);
        return in;
    }

    // Reader

    /**
     * @def new CharInReader(newCharIn())
     */
    protected Reader _newReader(OpenOption... options)
            throws IOException {
        ICharIn charIn = newCharIn(options);
        if (charIn == null)
            throw new NullPointerException("charIn");
        return new CharInReader(charIn);
    }

    public final Reader newReader(OpenOption... options)
            throws IOException {
        beforeOpenInput(options);
        Reader in = _newReader(options);
        afterOpenInput(in);
        return in;
    }

    // BufferedReader

    /**
     * @def new BufferedReader(newReader())
     */
    protected BufferedReader _newBufferedReader(OpenOption... options)
            throws IOException {
        Reader reader = _newReader(options);
        if (reader == null)
            throw new NullPointerException("reader");
        if (reader instanceof BufferedReader)
            return (BufferedReader) reader;
        return new BufferedReader(reader);
    }

    public final BufferedReader newBufferedReader(OpenOption... options)
            throws IOException {
        beforeOpenInput(options);
        BufferedReader in = _newBufferedReader(options);
        afterOpenInput(in);
        return in;
    }

    // DataInput

    /**
     * @def new DataInputStream(newInputStream())
     */
    protected DataInputStream _newDataInput(OpenOption... options)
            throws IOException {
        InputStream inputStream = _newInputStream(options);
        if (inputStream == null)
            throw new NullPointerException("inputStream");

        DataInputStream dataIn;
        if (inputStream instanceof DataInputStream)
            dataIn = (DataInputStream) inputStream;
        else
            dataIn = new DataInputStream(inputStream);

        return dataIn;
    }

    public final DataInput newDataInput(OpenOption... options)
            throws IOException {
        beforeOpenInput(options);
        DataInputStream in = _newDataInput(options);
        afterOpenInput(in);
        return in;
    }

    // ObjectInput

    /**
     * @def new ObjectInputStream(newInputStream())
     */
    protected ObjectInput _newObjectInput(OpenOption... options)
            throws IOException {
        InputStream inputStream = _newInputStream(options);
        if (inputStream == null)
            throw new NullPointerException("inputStream");
        if (inputStream instanceof ObjectInput)
            return (ObjectInput) inputStream;
        return new ObjectInputStream(inputStream);
    }

    public final ObjectInput newObjectInput(OpenOption... options)
            throws IOException {
        beforeOpenInput(options);
        ObjectInput in = _newObjectInput(options);
        afterOpenInput(in);
        return in;
    }

    // LAReader

    /**
     * @def new LAReader(newReader())
     */
    protected LAReader _newLAReader(OpenOption... options)
            throws IOException {
        Reader reader = _newReader(options);
        if (reader == null)
            throw new NullPointerException("reader");
        if (reader instanceof LAReader)
            return (LAReader) reader;
        return new LAReader(reader);
    }

    public final LAReader newLAReader(OpenOption... options)
            throws IOException {
        beforeOpenInput(options);
        LAReader in = _newLAReader(options);
        afterOpenInput(in);
        return in;
    }

    // LineReader

    /**
     * @def new LineReader(newReader())
     */
    protected LineReader _newLineReader(OpenOption... options)
            throws IOException {
        Reader reader = _newReader(options);
        if (reader == null)
            throw new NullPointerException("reader");
        if (reader instanceof LineReader)
            return (LineReader) reader;
        return new LineReader(reader);
    }

    public final LineReader newLineReader(OpenOption... options)
            throws IOException {
        beforeOpenInput(options);
        LineReader in = _newLineReader(options);
        afterOpenInput(in);
        return in;
    }

    // IByteOut

    protected abstract IByteOut _newByteOut(OpenOption... options)
            throws IOException;

    public final IByteOut newByteOut(OpenOption... options)
            throws IOException {
        beforeOpenOutput(options);
        IByteOut out = _newByteOut(options);
        afterOpenOutput(out);
        return out;
    }

    // ICharOut

    protected abstract ICharOut _newCharOut(OpenOption... options)
            throws IOException;

    public final ICharOut newCharOut(OpenOption... options)
            throws IOException {
        beforeOpenOutput(options);
        ICharOut out = _newCharOut(options);
        afterOpenOutput(out);
        return out;
    }

    // IPrintOut

    protected IPrintOut _newPrintOut(OpenOption... options)
            throws IOException {
        ICharOut charOut = newCharOut(options);
        return PrintOutImpl.from(charOut);
    }

    public final IPrintOut newPrintOut(OpenOption... options)
            throws IOException {
        beforeOpenOutput(options);
        IPrintOut out = _newPrintOut(options);
        afterOpenOutput(out);
        return out;
    }

    // Writer

    protected Writer _newWriter(OpenOption... options)
            throws IOException {
        ICharOut charOut = newCharOut(options);
        if (charOut == null)
            throw new NullPointerException("charOut");
        return new CharOutWriter(charOut);
    }

    public final Writer newWriter(OpenOption... options)
            throws IOException {
        beforeOpenOutput(options);
        Writer out = _newWriter(options);
        afterOpenOutput(out);
        return out;
    }

    // BufferedWriter

    protected BufferedWriter _newBufferedWriter(OpenOption... options)
            throws IOException {
        Writer writer = _newWriter(options);
        return new BufferedWriter(writer);
    }

    public final BufferedWriter newBufferedWriter(OpenOption... options)
            throws IOException {
        beforeOpenOutput(options);
        BufferedWriter out = _newBufferedWriter(options);
        afterOpenOutput(out);
        return out;
    }

    // OutputStream

    protected OutputStream _newOutputStream(OpenOption... options)
            throws IOException {
        IByteOut byteOut = newByteOut(options);
        if (byteOut == null)
            throw new NullPointerException("byteOut");
        return new ByteOutOutputStream(byteOut);
    }

    public final OutputStream newOutputStream(OpenOption... options)
            throws IOException {
        beforeOpenOutput(options);
        OutputStream out = _newOutputStream(options);
        afterOpenOutput(out);
        return out;
    }

    // PrintStream

    protected PrintStream _newPrintStream(OpenOption... options)
            throws IOException {
        OutputStream outputStream = _newOutputStream(options);
        if (outputStream == null)
            throw new NullPointerException("outputStream");
        if (outputStream instanceof PrintStream)
            return (PrintStream) outputStream;
        return new PrintStream(outputStream);
    }

    public final PrintStream newPrintStream(OpenOption... options)
            throws IOException {
        beforeOpenOutput(options);
        PrintStream out = _newPrintStream(options);
        afterOpenOutput(out);
        return out;
    }

    // IDataOut

    protected IDataOut _newDataOutLE(OpenOption... options)
            throws IOException {
        IByteOut byteOut = newByteOut(options);
        return DataOutImplLE.from(byteOut);
    }

    public final IDataOut newDataOutLE(OpenOption... options)
            throws IOException {
        beforeOpenOutput(options);
        IDataOut out = _newDataOutLE(options);
        afterOpenOutput(out);
        return out;
    }

    protected IDataOut _newDataOutBE(OpenOption... options)
            throws IOException {
        IByteOut byteOut = newByteOut(options);
        return DataOutImplBE.from(byteOut);
    }

    public final IDataOut newDataOutBE(OpenOption... options)
            throws IOException {
        beforeOpenOutput(options);
        IDataOut out = _newDataOutBE(options);
        afterOpenOutput(out);
        return out;
    }

    // IDataOutput

    protected IDataOutput _newDataOutput(OpenOption... options)
            throws IOException {
        OutputStream outputStream = _newOutputStream(options);
        if (outputStream == null)
            throw new NullPointerException("outputStream");

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

    public final IDataOutput newDataOutput(OpenOption... options)
            throws IOException {
        beforeOpenOutput(options);
        IDataOutput out = _newDataOutput(options);
        afterOpenOutput(out);
        return out;
    }

    // IObjectOutput

    protected IObjectOutput _newObjectOutput(OpenOption... options)
            throws IOException {
        OutputStream outputStream = _newOutputStream(options);
        if (outputStream == null)
            throw new NullPointerException("outputStream");

        if (outputStream instanceof IObjectOutput)
            return (IObjectOutput) outputStream;

        ObjectOutputStream oos;
        if (outputStream instanceof ObjectOutputStream)
            oos = (ObjectOutputStream) outputStream;
        else
            oos = new ObjectOutputStream(outputStream);

        return new ObjectOutputAdapter(oos);
    }

    public final IObjectOutput newObjectOutput(OpenOption... options)
            throws IOException {
        beforeOpenOutput(options);
        IObjectOutput out = _newObjectOutput(options);
        afterOpenOutput(out);
        return out;
    }

    // IByteIOS, ICharIOS

    public final IByteIOS newByteIOS(OpenOption... options)
            throws IOException {
        beforeOpenOutput(options);
        IByteIOS ios = _newByteIOS(options);
        afterOpenOutput(ios);
        return ios;
    }

    public final ICharIOS newCharIOS(OpenOption... options)
            throws IOException {
        beforeOpenOutput(options);
        ICharIOS ios = _newCharIOS(options);
        afterOpenOutput(ios);
        return ios;
    }

    protected IByteIOS _newByteIOS(OpenOption... options)
            throws IOException {
        throw new NotImplementedException();
    }

    protected ICharIOS _newCharIOS(OpenOption... options)
            throws IOException {
        throw new NotImplementedException();
    }

}
