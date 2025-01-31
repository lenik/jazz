package net.bodz.bas.io.res;

import java.io.IOException;
import java.nio.file.OpenOption;

import net.bodz.bas.io.IByteIOS;
import net.bodz.bas.io.ICharIOS;

public interface IRandomResource
        extends IStreamResource {

    IByteIOS newByteIOS(OpenOption... options)
            throws IOException;

    ICharIOS newCharIOS(OpenOption... options)
            throws IOException;

}
