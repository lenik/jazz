package net.bodz.bas.vfs.impl.filter;

import java.io.InputStream;
import java.io.OutputStream;

import net.bodz.bas.vfs.path.IPath;

public interface IVfsFilter {

    /**
     * Encode actual path.
     * 
     * @param path
     *            Non-<code>null</code> actual file path.
     * @return Encoded path which is used as the local path within the filter-device.
     */
    String encodePath(IPath path);

    /**
     * Decode filter-encoded path.
     * 
     * @param encodedPath
     *            Encoded path which is used as the local path within the filter-device.
     * @return Non-<code>null</code> actual file path.
     */
    IPath decodePath(String encodedPath);

    OutputStream encodeData(OutputStream decodedOutput);

    InputStream decodeData(InputStream encodedInput);

}
