package net.bodz.bas.vfs.util.content;

import java.io.IOException;

public interface IProbing {

    /**
     * @return <code>null</code> if MIME-Type is unknown.
     */
    String getContentType()
            throws IOException;

    boolean isText()
            throws IOException;

    boolean isBinary()
            throws IOException;

}
