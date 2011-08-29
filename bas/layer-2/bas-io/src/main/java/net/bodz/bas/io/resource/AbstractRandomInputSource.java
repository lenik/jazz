package net.bodz.bas.io.resource;

import java.io.IOException;
import java.util.jar.JarFile;
import java.util.zip.ZipFile;

import net.bodz.bas.err.BadFormatException;

public abstract class AbstractRandomInputSource
        implements IRandomInputSource {

    @Override
    public JarFile newJarFile()
            throws BadFormatException, IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public ZipFile newZipFile()
            throws BadFormatException, IOException {
        throw new UnsupportedOperationException();
    }

}
