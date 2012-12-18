package net.bodz.bas.io.resource;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.java.io.DataOutputAdapter;
import net.bodz.bas.c.java.io.IDataOutput;
import net.bodz.bas.c.java.io.IObjectOutput;
import net.bodz.bas.c.java.io.ObjectOutputAdapter;
import net.bodz.bas.io.LineReader;
import net.bodz.bas.io.lookahead.LAReader;
import net.bodz.bas.sio.*;
import net.bodz.bas.sugar.Tooling;

public abstract class StreamResourceImplHelper {

    static final Charset utf8Charset = Charset.forName("UTF-8");

    private Charset charset;
    private List<IOpenResourceListener> openResourceListeners = new ArrayList<IOpenResourceListener>(1);

    public StreamResourceImplHelper() {
        charset = getPreferredCharset();
        if (charset == null)
            throw new NullPointerException("preferredCharset");
    }

    public Tooling tooling() {
        return new Tooling(this);
    }

    /**
     * @return Preferred non-<code>null</code> {@link Charset}, the default implementation returns
     *         UTF-8 charset.
     */
    public Charset getPreferredCharset() {
        return utf8Charset;
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

    protected void fireOpenResource(boolean output, boolean append)
            throws IOException {
        OpenResourceEvent event = new OpenResourceEvent(this, output, append);
        for (IOpenResourceListener listener : openResourceListeners)
            listener.openResource(event);
    }

    protected void beforeOpenInput()
            throws IOException {
        fireOpenResource(false, false);
    }

    protected void beforeOpenOutput(boolean append)
            throws IOException {
        fireOpenResource(true, append);
    }

    protected void afterOpenInput(AutoCloseable in)
            throws IOException {
    }

    protected void afterOpenOutput(AutoCloseable out)
            throws IOException {
    }

    // input

    protected abstract IByteIn _newByteIn()
            throws IOException;

    public final IByteIn newByteIn()
            throws IOException {
        beforeOpenInput();
        IByteIn in = _newByteIn();
        afterOpenInput(in);
        return in;
    }

    protected abstract ICharIn _newCharIn()
            throws IOException;

    public final ICharIn newCharIn()
            throws IOException {
        beforeOpenInput();
        ICharIn in = _newCharIn();
        afterOpenInput(in);
        return in;
    }

    /**
     * @def newByteInInputStream(newByteIn())
     */
    protected InputStream _newInputStream()
            throws IOException {
        IByteIn byteIn = newByteIn();
        if (byteIn == null)
            throw new NullPointerException("byteIn");
        return new ByteInInputStream(byteIn);
    }

    public final InputStream newInputStream()
            throws IOException {
        beforeOpenInput();
        InputStream in = _newInputStream();
        afterOpenInput(in);
        return in;
    }

    /**
     * @def new CharInReader(newCharIn())
     */
    protected Reader _newReader()
            throws IOException {
        ICharIn charIn = newCharIn();
        if (charIn == null)
            throw new NullPointerException("charIn");
        return new CharInReader(charIn);
    }

    public final Reader newReader()
            throws IOException {
        beforeOpenInput();
        Reader in = _newReader();
        afterOpenInput(in);
        return in;
    }

    /**
     * @def new BufferedReader(newReader())
     */
    protected BufferedReader _newBufferedReader()
            throws IOException {
        Reader reader = _newReader();
        if (reader == null)
            throw new NullPointerException("reader");
        if (reader instanceof BufferedReader)
            return (BufferedReader) reader;
        return new BufferedReader(reader);
    }

    public final BufferedReader newBufferedReader()
            throws IOException {
        beforeOpenInput();
        BufferedReader in = _newBufferedReader();
        afterOpenInput(in);
        return in;
    }

    /**
     * @def new DataInputStream(newInputStream())
     */
    protected DataInputStream _newDataInput()
            throws IOException {
        InputStream inputStream = _newInputStream();
        if (inputStream == null)
            throw new NullPointerException("inputStream");

        DataInputStream dataIn;
        if (inputStream instanceof DataInputStream)
            dataIn = (DataInputStream) inputStream;
        else
            dataIn = new DataInputStream(inputStream);

        return dataIn;
    }

    public final DataInputStream newDataInput()
            throws IOException {
        beforeOpenInput();
        DataInputStream in = _newDataInput();
        afterOpenInput(in);
        return in;
    }

    /**
     * @def new ObjectInputStream(newInputStream())
     */
    protected ObjectInput _newObjectInput()
            throws IOException {
        InputStream inputStream = _newInputStream();
        if (inputStream == null)
            throw new NullPointerException("inputStream");
        if (inputStream instanceof ObjectInput)
            return (ObjectInput) inputStream;
        return new ObjectInputStream(inputStream);
    }

    public final ObjectInput newObjectInput()
            throws IOException {
        beforeOpenInput();
        ObjectInput in = _newObjectInput();
        afterOpenInput(in);
        return in;
    }

    /**
     * @def new LAReader(newReader())
     */
    protected LAReader _newLAReader()
            throws IOException {
        Reader reader = _newReader();
        if (reader == null)
            throw new NullPointerException("reader");
        if (reader instanceof LAReader)
            return (LAReader) reader;
        return new LAReader(reader);
    }

    public final LAReader newLAReader()
            throws IOException {
        beforeOpenInput();
        LAReader in = _newLAReader();
        afterOpenInput(in);
        return in;
    }

    /**
     * @def new LineReader(newReader())
     */
    protected LineReader _newLineReader()
            throws IOException {
        Reader reader = _newReader();
        if (reader == null)
            throw new NullPointerException("reader");
        if (reader instanceof LineReader)
            return (LineReader) reader;
        return new LineReader(reader);
    }

    public final LineReader newLineReader()
            throws IOException {
        beforeOpenInput();
        LineReader in = _newLineReader();
        afterOpenInput(in);
        return in;
    }

    // output

    protected boolean isAppendMode() {
        return false;
    }

    // IByteOut

    protected abstract IByteOut _newByteOut(boolean append)
            throws IOException;

    public final IByteOut newByteOut(boolean append)
            throws IOException {
        beforeOpenOutput(append);
        IByteOut out = _newByteOut(append);
        afterOpenOutput(out);
        return out;
    }

    public final IByteOut newByteOut()
            throws IOException {
        return newByteOut(isAppendMode());
    }

    // (CharOut

    protected abstract ICharOut _newCharOut(boolean append)
            throws IOException;

    public final ICharOut newCharOut(boolean append)
            throws IOException {
        beforeOpenOutput(append);
        ICharOut out = _newCharOut(append);
        afterOpenOutput(out);
        return out;
    }

    public final ICharOut newCharOut()
            throws IOException {
        return newCharOut(isAppendMode());
    }

    // IPrintOut

    protected IPrintOut _newPrintOut(boolean append)
            throws IOException {
        ICharOut charOut = newCharOut(append);
        return PrintOutImpl.from(charOut);
    }

    public final IPrintOut newPrintOut(boolean append)
            throws IOException {
        beforeOpenOutput(append);
        IPrintOut out = _newPrintOut(append);
        afterOpenOutput(out);
        return out;
    }

    public final IPrintOut newPrintOut()
            throws IOException {
        return newPrintOut(isAppendMode());
    }

    // Writer

    protected Writer _newWriter(boolean append)
            throws IOException {
        ICharOut charOut = newCharOut(append);
        if (charOut == null)
            throw new NullPointerException("charOut");
        return new CharOutWriter(charOut);
    }

    public final Writer newWriter(boolean append)
            throws IOException {
        beforeOpenOutput(append);
        Writer out = _newWriter(append);
        afterOpenOutput(out);
        return out;
    }

    public final Writer newWriter()
            throws IOException {
        return newWriter(isAppendMode());
    }

    // OutputStream

    protected OutputStream _newOutputStream(boolean append)
            throws IOException {
        IByteOut byteOut = newByteOut(append);
        if (byteOut == null)
            throw new NullPointerException("byteOut");
        return new ByteOutOutputStream(byteOut);
    }

    public final OutputStream newOutputStream(boolean append)
            throws IOException {
        beforeOpenOutput(append);
        OutputStream out = _newOutputStream(append);
        afterOpenOutput(out);
        return out;
    }

    public final OutputStream newOutputStream()
            throws IOException {
        return newOutputStream(isAppendMode());
    }

    // PrintStream

    protected PrintStream _newPrintStream(boolean append)
            throws IOException {
        OutputStream outputStream = _newOutputStream(append);
        if (outputStream == null)
            throw new NullPointerException("outputStream");
        if (outputStream instanceof PrintStream)
            return (PrintStream) outputStream;
        return new PrintStream(outputStream);
    }

    public final PrintStream newPrintStream(boolean append)
            throws IOException {
        beforeOpenOutput(append);
        PrintStream out = _newPrintStream(append);
        afterOpenOutput(out);
        return out;
    }

    public final PrintStream newPrintStream()
            throws IOException {
        return newPrintStream(isAppendMode());
    }

    // IByteOutEx

    protected IByteOutEx _newByteOutNative(boolean append)
            throws IOException {
        IByteOut byteOut = newByteOut(append);
        return ByteOutExImpl.from(byteOut);
    }

    public final IByteOutEx newByteOutNative(boolean append)
            throws IOException {
        beforeOpenOutput(append);
        IByteOutEx out = _newByteOutNative(append);
        afterOpenOutput(out);
        return out;
    }

    public final IByteOutEx newByteOutNative()
            throws IOException {
        return newByteOutNative(isAppendMode());
    }

    // IDataOutput

    protected IDataOutput _newDataOutput(boolean append)
            throws IOException {
        OutputStream outputStream = _newOutputStream(append);
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

    public final IDataOutput newDataOutput(boolean append)
            throws IOException {
        beforeOpenOutput(append);
        IDataOutput out = _newDataOutput(append);
        afterOpenOutput(out);
        return out;
    }

    public final IDataOutput newDataOutput()
            throws IOException {
        return newDataOutput(isAppendMode());
    }

    // IObjectOutput

    protected IObjectOutput _newObjectOutput(boolean append)
            throws IOException {
        OutputStream outputStream = _newOutputStream(append);
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

    public final IObjectOutput newObjectOutput(boolean append)
            throws IOException {
        beforeOpenOutput(append);
        IObjectOutput out = _newObjectOutput(append);
        afterOpenOutput(out);
        return out;
    }

    public final IObjectOutput newObjectOutput()
            throws IOException {
        return newObjectOutput(isAppendMode());
    }

}
