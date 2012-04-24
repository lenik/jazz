package net.bodz.bas.vfs.preparation;

import java.io.IOException;

public interface IProbePreparation {

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
