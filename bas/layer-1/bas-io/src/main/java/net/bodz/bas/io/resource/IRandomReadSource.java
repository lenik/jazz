package net.bodz.bas.io.resource;

import java.io.IOException;
import java.util.jar.JarFile;
import java.util.zip.ZipFile;

import net.bodz.bas.exceptions.BadFormatException;

public interface IRandomReadSource {

    /**
     * @return non-<code>null</code> value.
     */
    ZipFile newZipFile()
            throws BadFormatException, IOException;

    /**
     * @return non-<code>null</code> value.
     */
    JarFile newJarFile()
            throws BadFormatException, IOException;

}
