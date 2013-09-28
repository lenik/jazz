package net.bodz.bas.io.res;

import java.io.*;
import java.nio.file.OpenOption;

import net.bodz.bas.c.java.io.IDataOutput;
import net.bodz.bas.c.java.io.IObjectOutput;
import net.bodz.bas.c.java.io.LineReader;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.IDataOut;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.impl.LAReader;

public abstract class ProxyStreamResource
        extends AbstractStreamResource// implements IWrapper<IStreamResource>
{

    protected IStreamResource _orig;

    public ProxyStreamResource(IStreamResource _orig) {
        this._orig = _orig;
    }

    public IStreamResource getWrapped()
            throws IOException {
        return _orig;
    }

    @Override
    protected IByteIn _newByteIn(OpenOption... options)
            throws IOException {
        return getWrapped().newByteIn(options);
    }

    @Override
    protected IByteOut _newByteOut(OpenOption... options)
            throws IOException {
        return getWrapped().newByteOut(options);
    }

    @Override
    protected ICharIn _newCharIn(OpenOption... options)
            throws IOException {
        return getWrapped().newCharIn(options);
    }

    @Override
    protected ICharOut _newCharOut(OpenOption... options)
            throws IOException {
        return getWrapped().newCharOut(options);
    }

    @Override
    protected InputStream _newInputStream(OpenOption... options)
            throws IOException {
        return getWrapped().newInputStream(options);
    }

    @Override
    protected IPrintOut _newPrintOut(OpenOption... options)
            throws IOException {
        return getWrapped().newPrintOut(options);
    }

    @Override
    protected ObjectInput _newObjectInput(OpenOption... options)
            throws IOException {
        return getWrapped().newObjectInput(options);
    }

    @Override
    protected Reader _newReader(OpenOption... options)
            throws IOException {
        return getWrapped().newReader(options);
    }

    @Override
    protected IDataOut _newDataOutLE(OpenOption... options)
            throws IOException {
        return getWrapped().newDataOutLE(options);
    }

    @Override
    protected IDataOut _newDataOutBE(OpenOption... options)
            throws IOException {
        return getWrapped().newDataOutBE(options);
    }

    @Override
    protected BufferedReader _newBufferedReader(OpenOption... options)
            throws IOException {
        return getWrapped().newBufferedReader(options);
    }

    @Override
    protected LineReader _newLineReader(OpenOption... options)
            throws IOException {
        return getWrapped().newLineReader(options);
    }

    @Override
    protected LAReader _newLAReader(OpenOption... options)
            throws IOException {
        return getWrapped().newLAReader(options);
    }

    @Override
    protected OutputStream _newOutputStream(OpenOption... options)
            throws IOException {
        return getWrapped().newOutputStream(options);
    }

    @Override
    protected IDataOutput _newDataOutput(OpenOption... options)
            throws IOException {
        return getWrapped().newDataOutput(options);
    }

    @Override
    protected IObjectOutput _newObjectOutput(OpenOption... options)
            throws IOException {
        return getWrapped().newObjectOutput(options);
    }

    @Override
    protected PrintStream _newPrintStream(OpenOption... options)
            throws IOException {
        return getWrapped().newPrintStream(options);
    }

    @Override
    protected Writer _newWriter(OpenOption... options)
            throws IOException {
        return getWrapped().newWriter(options);
    }

}
