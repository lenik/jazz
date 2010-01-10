package net.bodz.bas.fs;

import java.io.IOException;

public interface IProbeToolkit {

    /**
     * @return <code>null</code> if MIME-Type is unknown.
     */
    String getMIMEType()
            throws IOException;

    boolean isText()
            throws IOException;

    boolean isBinary()
            throws IOException;

}
