package net.bodz.bas.io.resource;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

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

    protected boolean isAppendMode() {
        return false;
    }

    protected void fireOpenResource(boolean output)
            throws IOException {
        OpenResourceEvent event = new OpenResourceEvent(this, output, isAppendMode());
        for (IOpenResourceListener listener : openResourceListeners)
            listener.openResource(event);
    }

    public Tooling tooling() {
        return new Tooling(this);
    }

    // input

    protected abstract IByteIn _newByteIn()
            throws IOException;

    public final IByteIn newByteIn()
            throws IOException {
        fireOpenResource(false);
        return _newByteIn();
    }

    protected abstract ICharIn _newCharIn()
            throws IOException;

    public final ICharIn newCharIn()
            throws IOException {
        fireOpenResource(false);
        return _newCharIn();
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
        fireOpenResource(false);
        return _newInputStream();
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
        fireOpenResource(false);
        return _newReader();
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
        fireOpenResource(false);
        return _newBufferedReader();
    }

    /**
     * @def new DataInputStream(newInputStream())
     */
    protected DataInput _newDataInput()
            throws IOException {
        InputStream inputStream = _newInputStream();
        if (inputStream == null)
            throw new NullPointerException("inputStream");
        if (inputStream instanceof DataInput)
            return (DataInput) inputStream;
        DataInputStream dataIn = new DataInputStream(inputStream);
        return dataIn;
    }

    public final DataInput newDataInput()
            throws IOException {
        fireOpenResource(false);
        return _newDataInput();
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
        fireOpenResource(false);
        return _newObjectInput();
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
        fireOpenResource(false);
        return _newLAReader();
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
        fireOpenResource(false);
        return _newLineReader();
    }

    // output

    protected abstract IByteOut _newByteOut()
            throws IOException;

    public final IByteOut newByteOut()
            throws IOException {
        fireOpenResource(true);
        return _newByteOut();
    }

    protected abstract ICharOut _newCharOut()
            throws IOException;

    public final ICharOut newCharOut()
            throws IOException {
        fireOpenResource(true);
        return _newCharOut();
    }

    protected IPrintOut _newPrintOut()
            throws IOException {
        ICharOut charOut = newCharOut();
        return PrintOutImpl.from(charOut);
    }

    public final IPrintOut newPrintOut()
            throws IOException {
        fireOpenResource(true);
        return _newPrintOut();
    }

    protected Writer _newWriter()
            throws IOException {
        ICharOut charOut = newCharOut();
        if (charOut == null)
            throw new NullPointerException("charOut");
        return new CharOutWriter(charOut);
    }

    public final Writer newWriter()
            throws IOException {
        fireOpenResource(true);
        return _newWriter();
    }

    protected OutputStream _newOutputStream()
            throws IOException {
        IByteOut byteOut = newByteOut();
        if (byteOut == null)
            throw new NullPointerException("byteOut");
        return new ByteOutOutputStream(byteOut);
    }

    public final OutputStream newOutputStream()
            throws IOException {
        fireOpenResource(true);
        return _newOutputStream();
    }

    protected PrintStream _newPrintStream()
            throws IOException {
        OutputStream outputStream = _newOutputStream();
        if (outputStream == null)
            throw new NullPointerException("outputStream");
        if (outputStream instanceof PrintStream)
            return (PrintStream) outputStream;
        return new PrintStream(outputStream);
    }

    public final PrintStream newPrintStream()
            throws IOException {
        fireOpenResource(true);
        return _newPrintStream();
    }

    protected IByteOutEx _newByteOutNative()
            throws IOException {
        IByteOut byteOut = newByteOut();
        return ByteOutExImpl.from(byteOut);
    }

    public final IByteOutEx newByteOutNative()
            throws IOException {
        fireOpenResource(true);
        return _newByteOutNative();
    }

    protected DataOutput _newDataOutput()
            throws IOException {
        OutputStream outputStream = _newOutputStream();
        if (outputStream == null)
            throw new NullPointerException("outputStream");
        if (outputStream instanceof DataOutput)
            return (DataOutput) outputStream;
        return new DataOutputStream(outputStream);
    }

    public final DataOutput newDataOutput()
            throws IOException {
        fireOpenResource(true);
        return _newDataOutput();
    }

    protected ObjectOutput _newObjectOutput()
            throws IOException {
        OutputStream outputStream = _newOutputStream();
        if (outputStream == null)
            throw new NullPointerException("outputStream");
        if (outputStream instanceof ObjectOutput)
            return (ObjectOutput) outputStream;
        return new ObjectOutputStream(outputStream);
    }

    public final ObjectOutput newObjectOutput()
            throws IOException {
        fireOpenResource(true);
        return _newObjectOutput();
    }

}
