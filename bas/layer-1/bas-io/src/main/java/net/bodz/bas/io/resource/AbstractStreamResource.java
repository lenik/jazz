package net.bodz.bas.io.resource;

import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.nio.charset.Charset;

import net.bodz.bas.io.resource.preparation.IFormatDumpPreparation;
import net.bodz.bas.io.resource.preparation.IStreamWritePreparation;
import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.IByteOutNative;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.sio.ILineCharOut;

public abstract class AbstractStreamResource
        extends AbstractStreamInputSource
        implements IStreamOutputTarget {

    private final IStreamOutputTarget asotImpl;

    public AbstractStreamResource() {
        super();
        asotImpl = new AbstractStreamOutputTarget() {

            @Override
            public ICharOut newCharOut()
                    throws IOException {
                return AbstractStreamResource.this.newCharOut();
            }

            @Override
            public IByteOut newByteOut()
                    throws IOException {
                return AbstractStreamResource.this.newByteOut();
            }

        };
    }

    @Override
    public AbstractStreamResource clone() {
        return (AbstractStreamResource) super.clone();
    }

    @Override
    public boolean isAppendMode() {
        return asotImpl.isAppendMode();
    }

    @Override
    public IStreamOutputTarget setAppendMode(boolean appendMode) {
        asotImpl.setAppendMode(appendMode);
        return this;
    }

    @Override
    public AbstractStreamResource setCharset(Charset charset) {
        super.setCharset(charset);
        asotImpl.setCharset(charset);
        return this;
    }

    @Override
    public OutputStream newOutputStream()
            throws IOException {
        return asotImpl.newOutputStream();
    }

    @Override
    public Writer newWriter()
            throws IOException {
        return asotImpl.newWriter();
    }

    @Override
    public PrintStream newPrintStream()
            throws IOException {
        return asotImpl.newPrintStream();
    }

    @Override
    public DataOutput newDataOutput()
            throws IOException {
        return asotImpl.newDataOutput();
    }

    @Override
    public ObjectOutput newObjectOutput()
            throws IOException {
        return asotImpl.newObjectOutput();
    }

    @Override
    public ILineCharOut newLineCharOut()
            throws IOException {
        return asotImpl.newLineCharOut();
    }

    @Override
    public IByteOutNative newByteOutNative()
            throws IOException {
        return asotImpl.newByteOutNative();
    }

    @Override
    public IFormatDumpPreparation forDump()
            throws IOException {
        return asotImpl.forDump();
    }

    @Override
    public IStreamWritePreparation forWrite()
            throws IOException {
        return asotImpl.forWrite();
    }

}
