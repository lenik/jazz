package net.bodz.bas.io.resource;

import java.io.*;
import java.nio.charset.Charset;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.sio.ByteOutExImpl;
import net.bodz.bas.sio.ByteOutOutputStream;
import net.bodz.bas.sio.CharOutWriter;
import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.IByteOutEx;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.util.Tooling;

public abstract class AbstractStreamOutputTarget
        implements IStreamOutputTarget {

    private boolean appendMode;
    private Charset charset;

    public AbstractStreamOutputTarget() {
        charset = getPreferredCharset();
        if (charset == null)
            throw new NullPointerException("preferredCharset");
    }

    @Override
    public IStreamOutputTarget clone() {
        try {
            return (IStreamOutputTarget) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isAppendMode() {
        return appendMode;
    }

    @Override
    public void setAppendMode(boolean appendMode) {
        this.appendMode = appendMode;
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

    @Override
    public Writer newWriter()
            throws IOException {
        ICharOut charOut = newCharOut();
        if (charOut == null)
            throw new NullPointerException("charOut");
        return new CharOutWriter(charOut);
    }

    @Override
    public OutputStream newOutputStream()
            throws IOException {
        IByteOut byteOut = newByteOut();
        if (byteOut == null)
            throw new NullPointerException("byteOut");
        return new ByteOutOutputStream(byteOut);
    }

    @Override
    public PrintStream newPrintStream()
            throws IOException {
        OutputStream outputStream = newOutputStream();
        if (outputStream == null)
            throw new NullPointerException("outputStream");
        if (outputStream instanceof PrintStream)
            return (PrintStream) outputStream;
        return new PrintStream(outputStream);
    }

    @Override
    public IByteOutEx newByteOutNative()
            throws IOException {
        IByteOut byteOut = newByteOut();
        return ByteOutExImpl.from(byteOut);
    }

    @Override
    public DataOutput newDataOutput()
            throws IOException {
        OutputStream outputStream = newOutputStream();
        if (outputStream == null)
            throw new NullPointerException("outputStream");
        if (outputStream instanceof DataOutput)
            return (DataOutput) outputStream;
        return new DataOutputStream(outputStream);
    }

    @Override
    public ObjectOutput newObjectOutput()
            throws IOException {
        OutputStream outputStream = newOutputStream();
        if (outputStream == null)
            throw new NullPointerException("outputStream");
        if (outputStream instanceof ObjectOutput)
            return (ObjectOutput) outputStream;
        return new ObjectOutputStream(outputStream);
    }

    @Override
    public ICharOut newCharOut()
            throws IOException {
        return newPrintOut();
    }

    @Override
    public Tooling tooling() {
        return new Tooling(this);
    }

}
