package net.bodz.bas.io;

import java.io.IOException;

public interface ResFolder {

    /**
     * @param path
     *            a path relative to this folder.
     * @return should never be <code>null</code>
     * @throws IOException
     *             if <code>path</code> is invalid.
     */
    ResLink get(String path) throws IOException;

}
