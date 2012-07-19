package net.bodz.bas.io.resource;

import java.io.*;
import java.nio.charset.Charset;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.LineReader;
import net.bodz.bas.io.lookahead.LAReader;
import net.bodz.bas.sio.ByteInInputStream;
import net.bodz.bas.sio.CharInReader;
import net.bodz.bas.sio.IByteIn;
import net.bodz.bas.sio.ICharIn;
import net.bodz.bas.sugar.Tooling;

public abstract class AbstractStreamInputSource
        implements IStreamInputSource {

    private Charset charset;

    public AbstractStreamInputSource() {
        charset = getPreferredCharset();
        if (charset == null)
            throw new NullPointerException("preferredCharset");
    }

    @Override
    public IStreamInputSource clone() {
        try {
            return (AbstractStreamInputSource) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

    static final Charset utf8Charset = Charset.forName("UTF-8");

    /**
     * @return Preferred non-<code>null</code> {@link Charset}, the default implementation returns
     *         UTF-8 charset.
     */
    public Charset getPreferredCharset() {
        return utf8Charset;
    }

    @Override
    public Charset getCharset() {
        return charset;
    }

    @Override
    public void setCharset(Charset charset) {
        if (charset == null)
            throw new NullPointerException("charset");
        this.charset = charset;
    }

    @Override
    public void setCharset(String charsetName) {
        if (charsetName == null)
            throw new NullPointerException("charsetName");
        this.charset = Charset.forName(charsetName);
    }

    /**
     * @def newByteInInputStream(newByteIn())
     */
    @Override
    public InputStream newInputStream()
            throws IOException {
        IByteIn byteIn = newByteIn();
        if (byteIn == null)
            throw new NullPointerException("byteIn");
        return new ByteInInputStream(byteIn);
    }

    /**
     * @def new CharInReader(newCharIn())
     */
    @Override
    public Reader newReader()
            throws IOException {
        ICharIn charIn = newCharIn();
        if (charIn == null)
            throw new NullPointerException("charIn");
        return new CharInReader(charIn);
    }

    /**
     * @def new BufferedReader(newReader())
     */
    @Override
    public BufferedReader newBufferedReader()
            throws IOException {
        Reader reader = newReader();
        if (reader == null)
            throw new NullPointerException("reader");
        if (reader instanceof BufferedReader)
            return (BufferedReader) reader;
        return new BufferedReader(reader);
    }

    /**
     * @def new DataInputStream(newInputStream())
     */
    @Override
    public DataInput newDataInput()
            throws IOException {
        InputStream inputStream = newInputStream();
        if (inputStream == null)
            throw new NullPointerException("inputStream");
        if (inputStream instanceof DataInput)
            return (DataInput) inputStream;
        DataInputStream dataIn = new DataInputStream(inputStream);
        return dataIn;
    }

    /**
     * @def new ObjectInputStream(newInputStream())
     */
    @Override
    public ObjectInput newObjectInput()
            throws IOException {
        InputStream inputStream = newInputStream();
        if (inputStream == null)
            throw new NullPointerException("inputStream");
        if (inputStream instanceof ObjectInput)
            return (ObjectInput) inputStream;
        return new ObjectInputStream(inputStream);
    }

    /**
     * @def new LAReader(newReader())
     */
    @Override
    public LAReader newLAReader()
            throws IOException {
        Reader reader = newReader();
        if (reader == null)
            throw new NullPointerException("reader");
        if (reader instanceof LAReader)
            return (LAReader) reader;
        return new LAReader(reader);
    }

    /**
     * @def new LineReader(newReader())
     */
    @Override
    public LineReader newLineReader()
            throws IOException {
        Reader reader = newReader();
        if (reader == null)
            throw new NullPointerException("reader");
        if (reader instanceof LineReader)
            return (LineReader) reader;
        return new LineReader(reader);
    }

    @Override
    public Tooling tooling() {
        return new Tooling(this);
    }

}
