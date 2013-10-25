package net.bodz.bas.io.res;

import java.io.IOException;
import java.nio.file.OpenOption;

import net.bodz.bas.io.*;
import net.bodz.bas.io.impl.MergedByteIOS;
import net.bodz.bas.io.impl.MergedCharIOS;

public abstract class AbstractRandomResource
        extends AbstractStreamResource
        implements IRandomResource {

    @Override
    public long getLength() {
        return -1L;
    }

    @Override
    protected IByteIOS _newByteIOS(OpenOption... options)
            throws IOException {
        IByteIn in = _newByteIn(options);
        IByteOut out = _newByteOut(options);
        ISeekable seeker = getSeeker();
        ICroppable cropper = getCropper();
        return new MergedByteIOS(in, out, seeker, cropper);
    }

    @Override
    protected ICharIOS _newCharIOS(OpenOption... options)
            throws IOException {
        ICharIn in = _newCharIn(options);
        ICharOut out = _newCharOut(options);
        ISeekable seeker = getSeeker();
        ICroppable cropper = getCropper();
        return new MergedCharIOS(in, out, seeker, cropper);
    }

    protected abstract ISeekable getSeeker();

    protected abstract ICroppable getCropper();

}
