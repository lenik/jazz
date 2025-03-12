package net.bodz.bas.io.res;

import java.io.IOException;
import java.nio.file.OpenOption;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.io.IByteIOS;
import net.bodz.bas.io.ICharIOS;
import net.bodz.bas.meta.decl.NotNull;

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

    @NotNull
    default IByteIOS newByteIOS(OpenOption... options)
            throws IOException {
        throw new NotImplementedException();
    }

    @NotNull
    default ICharIOS newCharIOS(OpenOption... options)
            throws IOException {
        throw new NotImplementedException();
    }

}
