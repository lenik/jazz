package net.bodz.bas.io.res;

import java.io.IOException;
import java.nio.file.OpenOption;

import net.bodz.bas.io.IByteIOS;
import net.bodz.bas.io.ICharIOS;
import net.bodz.bas.meta.decl.NotNull;

public interface IRandomResource
        extends IStreamResource {

    @NotNull
    IByteIOS newByteIOS(OpenOption... options)
            throws IOException;

    @NotNull
    ICharIOS newCharIOS(OpenOption... options)
            throws IOException;

}
