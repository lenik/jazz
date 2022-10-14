package net.bodz.bas.codegen;

import java.io.File;
import java.nio.file.Path;

public interface IFileInfo {

    /**
     * Can be the entire directory.
     */
    File getBaseDir();

    /**
     * Can be filename only.
     */
    String getLocalPath();

    /**
     * The directory part of {@link #getPathInfo()}.
     *
     * @return <code>null</code> or <code>"."</code> for root directory.
     */
    String getLocalDirectory();

    /**
     * @return non-<code>null</code> value.
     */
    String getFileName();

    /**
     * Name part of {@link #getFileName()} without extension.
     */
    String getName();

    /**
     * Extension name without dot (<code>.</code>).
     *
     * @return <code>null</code> if none.
     */
    String getExtension();

    String getPath();

    /**
     * @return non-<code>null</code> value.
     */
    File toFile();

    Path toPath();

}
