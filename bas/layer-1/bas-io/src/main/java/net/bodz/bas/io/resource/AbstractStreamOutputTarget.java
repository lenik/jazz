package net.bodz.bas.io.resource;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.nio.charset.Charset;

import net.bodz.bas.io.resource.preparation.FormatDumpPreparation;
import net.bodz.bas.io.resource.preparation.IFormatDumpPreparation;
import net.bodz.bas.io.resource.preparation.IStreamWritePreparation;
import net.bodz.bas.io.resource.preparation.StreamWritePreparation;
import net.bodz.bas.sio.ByteOutNativeImpl;
import net.bodz.bas.sio.ByteOutOutputStream;
import net.bodz.bas.sio.CharOutImpl;
import net.bodz.bas.sio.CharOutWriter;
import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.IByteOutNative;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.sio.ILineCharOut;

public abstract class AbstractStreamOutputTarget
        implements IStreamOutputTarget {

    private Charset charset;

    public AbstractStreamOutputTarget() {
        charset = getPreferredCharset();
        if (charset == null)
            throw new NullPointerException("preferredCharset");
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
    public IByteOutNative newByteOutNative()
            throws IOException {
        IByteOut byteOut = newByteOut();
        if (byteOut == null)
            throw new NullPointerException("byteOut");
        if (byteOut instanceof IByteOutNative)
            return (IByteOutNative) byteOut;
        return new ByteOutNativeImpl(byteOut);
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
    public ILineCharOut newLineCharOut()
            throws IOException {
        ICharOut charOut = newCharOut();
        if (charOut instanceof ILineCharOut)
            return (ILineCharOut) charOut;
        return new CharOutImpl(charOut);
    }

    @Override
    public IFormatDumpPreparation forDump()
            throws IOException {
        return new FormatDumpPreparation(this);
    }

    @Override
    public IStreamWritePreparation forWrite()
            throws IOException {
        return new StreamWritePreparation(this);
    }

}
