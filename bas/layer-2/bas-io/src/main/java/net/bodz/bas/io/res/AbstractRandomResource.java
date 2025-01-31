package net.bodz.bas.io.res;

import java.io.IOException;
import java.nio.file.OpenOption;

import net.bodz.bas.io.*;
import net.bodz.bas.io.impl.MergedByteIOS;
import net.bodz.bas.io.impl.MergedCharIOS;

public abstract class AbstractRandomResource<This>
        extends AbstractStreamResource<This>
        implements IRandomResource {

    @Override
    public IByteIOS newByteIOS(OpenOption... options)
            throws IOException {
        IByteIn in = newByteIn(options);
        IByteOut out = newByteOut(options);
        ISeekable seeker = getSeeker();
        ICroppable cropper = getCropper();
        return new MergedByteIOS(in, out, seeker, cropper);
    }

    @Override
    public ICharIOS newCharIOS(OpenOption... options)
            throws IOException {
        ICharIn in = newCharIn(options);
        ICharOut out = newCharOut(options);
        ISeekable seeker = getSeeker();
        ICroppable cropper = getCropper();
        return new MergedCharIOS(in, out, seeker, cropper);
    }

    protected abstract ISeekable getSeeker();

    protected abstract ICroppable getCropper();

}
