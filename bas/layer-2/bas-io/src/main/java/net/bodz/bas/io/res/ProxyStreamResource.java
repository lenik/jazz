package net.bodz.bas.io.res;

import net.bodz.bas.c.java.io.IDataInput;
import net.bodz.bas.c.java.io.IDataOutput;
import net.bodz.bas.c.java.io.IObjectInput;
import net.bodz.bas.c.java.io.IObjectOutput;
import net.bodz.bas.c.java.io.LineReader;
import net.bodz.bas.io.IByteIOS;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICharIOS;
import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.bit.IBitIn;
import net.bodz.bas.io.impl.LAReader;
import net.bodz.bas.io.res.tools.IStreamReading;
import net.bodz.bas.io.res.tools.IStreamWriting;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.model.IWrapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.OpenOption;

public abstract class ProxyStreamResource<This>
        implements IStreamResource,
                   IWrapper<IStreamResource> {

    protected IStreamResource _orig;

    public ProxyStreamResource(IStreamResource _orig) {
        this._orig = _orig;
    }

    public IStreamResource getWrapped() {
        return _orig;
    }

    @Override
    public String getPath() {
        return getWrapped().getPath();
    }

    @Override
    public String getName() {
        return getWrapped().getName();
    }

    @Override
    public boolean isTextModePreferred() {
        return getWrapped().isTextModePreferred();
    }

    @Override
    public boolean isTextMode() {
        return getWrapped().isTextMode();
    }

    @NotNull
    @Override
    public Charset getPreferredCharset() {
        return getWrapped().getPreferredCharset();
    }

    @Override
    public Charset getCharset() {
        return getWrapped().getCharset();
    }

    @Override
    public Long getLength()
            throws IOException {
        return getWrapped().getLength();
    }

    @NotNull
    @Override
    public IByteIn newByteIn(OpenOption... options)
            throws IOException {
        return getWrapped().newByteIn(options);
    }

    @Override
    public IByteIOS newByteIOS(OpenOption... options)
            throws IOException {
        return getWrapped().newByteIOS(options);
    }

    @Override
    public ICharIOS newCharIOS(OpenOption... options)
            throws IOException {
        return getWrapped().newCharIOS(options);
    }

    @NotNull
    @Override
    public ICharIn newCharIn(OpenOption... options)
            throws IOException {
        return getWrapped().newCharIn(options);
    }

    @NotNull
    @Override
    public InputStream newInputStream(OpenOption... options)
            throws IOException {
        return getWrapped().newInputStream(options);
    }

    @NotNull
    @Override
    public IDataInput newDataInput(OpenOption... options)
            throws IOException {
        return getWrapped().newDataInput(options);
    }

    @NotNull
    @Override
    public IObjectInput newObjectInput(OpenOption... options)
            throws IOException {
        return getWrapped().newObjectInput(options);
    }

    @NotNull
    @Override
    public Reader newReader(OpenOption... options)
            throws IOException {
        return getWrapped().newReader(options);
    }

    @NotNull
    @Override
    public LAReader newLAReader(OpenOption... options)
            throws IOException {
        return getWrapped().newLAReader(options);
    }

    @NotNull
    @Override
    public BufferedReader newBufferedReader(OpenOption... options)
            throws IOException {
        return getWrapped().newBufferedReader(options);
    }

    @NotNull
    @Override
    public LineReader newLineReader(OpenOption... options)
            throws IOException {
        return getWrapped().newLineReader(options);
    }

    @NotNull
    @Override
    public IBitIn newBitIn(OpenOption... options)
            throws IOException {
        return getWrapped().newBitIn(options);
    }

    @Override
    public IDataIn newDataInLE(OpenOption... options)
            throws IOException {
        return getWrapped().newDataInLE(options);
    }

    @Override
    public IDataIn newDataInBE(OpenOption... options)
            throws IOException {
        return getWrapped().newDataInBE(options);
    }

    @Override
    public IByteOut newByteOut(OpenOption... options)
            throws IOException {
        return getWrapped().newByteOut(options);
    }

    @Override
    public ICharOut newCharOut(OpenOption... options)
            throws IOException {
        return getWrapped().newCharOut(options);
    }

    @NotNull
    @Override
    public IPrintOut newPrintOut(OpenOption... options)
            throws IOException {
        return getWrapped().newPrintOut(options);
    }

    @NotNull
    @Override
    public IDataOut newDataOutLE(OpenOption... options)
            throws IOException {
        return getWrapped().newDataOutLE(options);
    }

    @NotNull
    @Override
    public IDataOut newDataOutBE(OpenOption... options)
            throws IOException {
        return getWrapped().newDataOutBE(options);
    }

    @NotNull
    @Override
    public OutputStream newOutputStream(OpenOption... options)
            throws IOException {
        return getWrapped().newOutputStream(options);
    }

    @NotNull
    @Override
    public IDataOutput newDataOutput(OpenOption... options)
            throws IOException {
        return getWrapped().newDataOutput(options);
    }

    @NotNull
    @Override
    public IObjectOutput newObjectOutput(OpenOption... options)
            throws IOException {
        return getWrapped().newObjectOutput(options);
    }

    @NotNull
    @Override
    public PrintStream newPrintStream(OpenOption... options)
            throws IOException {
        return getWrapped().newPrintStream(options);
    }

    @NotNull
    @Override
    public Writer newWriter(OpenOption... options)
            throws IOException {
        return getWrapped().newWriter(options);
    }

    @NotNull
    @Override
    public BufferedWriter newBufferedWriter(OpenOption... options)
            throws IOException {
        return getWrapped().newBufferedWriter(options);
    }

    @Override
    public ITreeOut newTreeOut(OpenOption... options)
            throws IOException {
        return getWrapped().newTreeOut(options);
    }

}
