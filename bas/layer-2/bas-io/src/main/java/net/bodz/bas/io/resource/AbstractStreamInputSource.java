package net.bodz.bas.io.resource;

import java.io.IOException;
import java.nio.file.OpenOption;

import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.ICharOut;

public abstract class AbstractStreamInputSource
        extends StreamResourceTemplate
        implements IStreamInputSource {

    public AbstractStreamInputSource() {
    }

    @Override
    public IByteOut _newByteOut(OpenOption... options)
            throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public ICharOut _newCharOut(OpenOption... options)
            throws IOException {
        throw new UnsupportedOperationException();
    }

}
