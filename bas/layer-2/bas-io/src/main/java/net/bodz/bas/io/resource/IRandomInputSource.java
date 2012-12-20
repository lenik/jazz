package net.bodz.bas.io.resource;

import java.io.IOException;
import java.nio.file.OpenOption;
import java.util.jar.JarFile;
import java.util.zip.ZipFile;

import net.bodz.bas.err.BadFormatException;

public interface IRandomInputSource {

    /**
     * @return non-<code>null</code> value.
     */
    ZipFile newZipFile(OpenOption... options)
            throws BadFormatException, IOException;

    /**
     * @return non-<code>null</code> value.
     */
    JarFile newJarFile(OpenOption... options)
            throws BadFormatException, IOException;

}
