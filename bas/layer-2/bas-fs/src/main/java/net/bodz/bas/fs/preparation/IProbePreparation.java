package net.bodz.bas.fs.preparation;

import java.io.IOException;

public interface IProbePreparation {

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
