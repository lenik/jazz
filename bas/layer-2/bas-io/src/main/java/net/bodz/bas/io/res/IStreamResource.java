package net.bodz.bas.io.res;

import java.io.IOException;
import java.nio.file.OpenOption;

import net.bodz.bas.io.IByteIOS;
import net.bodz.bas.io.ICharIOS;

public interface IStreamResource
        extends IStreamInputSource, IStreamOutputTarget {

    Long getLength();

    void addOpenResourceListener(IOpenResourceListener listener);

    void removeOpenResourceListener(IOpenResourceListener listener);

    IByteIOS newByteIOS(OpenOption... options)
            throws IOException;

    ICharIOS newCharIOS(OpenOption... options)
            throws IOException;

}
