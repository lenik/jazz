package net.bodz.bas.io.res;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.io.IByteIOS;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICharIOS;
import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.ICroppable;
import net.bodz.bas.io.ISeekable;
import net.bodz.bas.io.impl.MergedByteIOS;

import java.io.IOException;
import java.nio.file.OpenOption;

public interface IStreamResource
        extends IStreamInputSource,
                IStreamOutputTarget {

    @Override
    default boolean canRead() {
        return true;
    }

    @Override
    default boolean canWrite() {
        return true;
    }

    default IByteIOS newByteIOS(OpenOption... options)
            throws IOException {
        throw new NotImplementedException();
    }

    default ICharIOS newCharIOS(OpenOption... options)
            throws IOException {
        throw new NotImplementedException();
    }

}
