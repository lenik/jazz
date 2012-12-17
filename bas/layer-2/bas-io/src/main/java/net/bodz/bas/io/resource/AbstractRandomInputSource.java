package net.bodz.bas.io.resource;

import java.io.IOException;
import java.util.jar.JarFile;
import java.util.zip.ZipFile;

import net.bodz.bas.err.BadFormatException;

public abstract class AbstractRandomInputSource
        implements IRandomInputSource {

    protected JarFile _newJarFile()
            throws BadFormatException, IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public final ZipFile newZipFile()
            throws BadFormatException, IOException {
        return _newZipFile();
    }

    protected ZipFile _newZipFile()
            throws BadFormatException, IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public final JarFile newJarFile()
            throws BadFormatException, IOException {
        return _newJarFile();
    }

}
