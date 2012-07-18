package net.bodz.bas.io.resource;

import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.nio.charset.Charset;

import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.IByteOutEx;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.sio.IPrintOut;

public abstract class AbstractStreamResource
        extends AbstractStreamInputSource
        implements IStreamResource {

    private final IStreamOutputTarget asotImpl;

    public AbstractStreamResource() {
        asotImpl = new AbstractStreamOutputTarget() {

            @Override
            public ICharOut newCharOut()
                    throws IOException {
                return AbstractStreamResource.this.newCharOut();
            }

            @Override
            public IPrintOut newPrintOut()
                    throws IOException {
                return AbstractStreamResource.this.newPrintOut();
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
    public void setAppendMode(boolean appendMode) {
        asotImpl.setAppendMode(appendMode);
    }

    @Override
    public void setCharset(Charset charset) {
        super.setCharset(charset);
        asotImpl.setCharset(charset);
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
    public ICharOut newCharOut()
            throws IOException {
        return asotImpl.newCharOut();
    }

    @Override
    public IPrintOut newPrintOut()
            throws IOException {
        return asotImpl.newPrintOut();
    }

    @Override
    public IByteOutEx newByteOutNative()
            throws IOException {
        return asotImpl.newByteOutNative();
    }

}
