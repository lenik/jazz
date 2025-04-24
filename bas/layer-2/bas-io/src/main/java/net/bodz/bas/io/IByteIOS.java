package net.bodz.bas.io;

import java.io.Closeable;

public interface IByteIOS
        extends IByteIn,
                IByteOut,
                ISeekable,
                ICroppable,
                Closeable {

}
