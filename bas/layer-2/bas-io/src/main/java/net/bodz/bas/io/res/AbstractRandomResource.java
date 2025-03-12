package net.bodz.bas.io.res;

import java.io.IOException;
import java.nio.file.OpenOption;

import net.bodz.bas.io.IByteIOS;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICharIOS;
import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.ICroppable;
import net.bodz.bas.io.ISeekable;
import net.bodz.bas.io.impl.MergedByteIOS;
import net.bodz.bas.io.impl.MergedCharIOS;
import net.bodz.bas.meta.decl.NotNull;

public abstract class AbstractRandomResource<This>
        extends AbstractStreamResource<This>
        implements IRandomResource {

    @NotNull
    @Override
    public IByteIOS newByteIOS(OpenOption... options)
            throws IOException {
        IByteIn in = newByteIn(options);
        IByteOut out = newByteOut(options);
        ISeekable seeker = getSeeker();
        ICroppable cropper = getCropper();
        return new MergedByteIOS(in, out, seeker, cropper);
    }

    @NotNull
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
