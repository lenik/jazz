package net.bodz.bas.io.res;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.OpenOption;

import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICharOut;

public abstract class AbstractStreamInputSource
        extends StreamResourceTemplate
        implements
            IStreamInputSource {

    public AbstractStreamInputSource() {
    }

    public AbstractStreamInputSource(String charsetName) {
        super(charsetName);
    }

    public AbstractStreamInputSource(Charset charset) {
        super(charset);
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
