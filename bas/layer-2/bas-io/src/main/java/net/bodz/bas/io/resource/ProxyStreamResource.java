package net.bodz.bas.io.resource;

import java.io.*;

import net.bodz.bas.c.java.io.IDataOutput;
import net.bodz.bas.c.java.io.IObjectOutput;
import net.bodz.bas.io.LineReader;
import net.bodz.bas.io.lookahead.LAReader;
import net.bodz.bas.sio.IByteIn;
import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.IByteOutEx;
import net.bodz.bas.sio.ICharIn;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.sio.IPrintOut;

public abstract class ProxyStreamResource
        extends AbstractStreamResource// implements IWrapper<IStreamResource>
{

    private IStreamResource _orig;

    public ProxyStreamResource(IStreamResource _orig) {
        this._orig = _orig;
    }

    public IStreamResource getWrapped()
            throws IOException {
        return _orig;
    }

    @Override
    public abstract IStreamResource clone();

    @Override
    protected IByteIn _newByteIn()
            throws IOException {
        return getWrapped().newByteIn();
    }

    @Override
    protected IByteOut _newByteOut(boolean append)
            throws IOException {
        return getWrapped().newByteOut(append);
    }

    @Override
    protected ICharIn _newCharIn()
            throws IOException {
        return getWrapped().newCharIn();
    }

    @Override
    protected ICharOut _newCharOut(boolean append)
            throws IOException {
        return getWrapped().newCharOut(append);
    }

    @Override
    protected InputStream _newInputStream()
            throws IOException {
        return getWrapped().newInputStream();
    }

    @Override
    protected IPrintOut _newPrintOut(boolean append)
            throws IOException {
        return getWrapped().newPrintOut(append);
    }

    @Override
    protected ObjectInput _newObjectInput()
            throws IOException {
        return getWrapped().newObjectInput();
    }

    @Override
    protected Reader _newReader()
            throws IOException {
        return getWrapped().newReader();
    }

    @Override
    protected IByteOutEx _newByteOutNative(boolean append)
            throws IOException {
        return getWrapped().newByteOutNative(append);
    }

    @Override
    protected BufferedReader _newBufferedReader()
            throws IOException {
        return getWrapped().newBufferedReader();
    }

    @Override
    protected LineReader _newLineReader()
            throws IOException {
        return getWrapped().newLineReader();
    }

    @Override
    protected LAReader _newLAReader()
            throws IOException {
        return getWrapped().newLAReader();
    }

    @Override
    protected OutputStream _newOutputStream(boolean append)
            throws IOException {
        return getWrapped().newOutputStream(append);
    }

    @Override
    protected IDataOutput _newDataOutput(boolean append)
            throws IOException {
        return getWrapped().newDataOutput(append);
    }

    @Override
    protected IObjectOutput _newObjectOutput(boolean append)
            throws IOException {
        return getWrapped().newObjectOutput(append);
    }

    @Override
    protected PrintStream _newPrintStream(boolean append)
            throws IOException {
        return getWrapped().newPrintStream(append);
    }

    @Override
    protected Writer _newWriter(boolean append)
            throws IOException {
        return getWrapped().newWriter(append);
    }

}
