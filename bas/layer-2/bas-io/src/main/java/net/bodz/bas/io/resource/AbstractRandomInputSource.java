package net.bodz.bas.io.resource;

import java.io.IOException;
import java.nio.file.OpenOption;
import java.util.jar.JarFile;
import java.util.zip.ZipFile;

import net.bodz.bas.err.BadFormatException;

public abstract class AbstractRandomInputSource
        implements IRandomInputSource {

    protected JarFile _newJarFile(OpenOption... options)
            throws BadFormatException, IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public final ZipFile newZipFile(OpenOption... options)
            throws BadFormatException, IOException {
        return _newZipFile(options);
    }

    protected ZipFile _newZipFile(OpenOption... options)
            throws BadFormatException, IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public final JarFile newJarFile(OpenOption... options)
            throws BadFormatException, IOException {
        return _newJarFile(options);
    }

}
