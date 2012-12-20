package net.bodz.bas.io.resource;

import java.io.IOException;
import java.nio.file.OpenOption;

import net.bodz.bas.sio.IByteIn;
import net.bodz.bas.sio.ICharIn;

public abstract class AbstractStreamOutputTarget
        extends StreamResourceImplHelper
        implements IStreamOutputTarget {

    public AbstractStreamOutputTarget() {
    }

    @Override
    public IByteIn _newByteIn(OpenOption... options)
            throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public ICharIn _newCharIn(OpenOption... options)
            throws IOException {
        throw new UnsupportedOperationException();
    }

}
