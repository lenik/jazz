package net.bodz.bas.io.res;

import java.io.IOException;
import java.nio.file.OpenOption;

import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.ICharIn;

public abstract class AbstractStreamOutputTarget
        extends StreamResourceTemplate
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
