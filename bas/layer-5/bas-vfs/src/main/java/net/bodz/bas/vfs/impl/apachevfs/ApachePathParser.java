package net.bodz.bas.vfs.impl.apachevfs;

import java.util.Set;

import net.bodz.bas.c.java.util.Collections;
import net.bodz.bas.vfs.path.AbstractGenericPathParser;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public class ApachePathParser
        extends AbstractGenericPathParser {

    private final ApacheVfsDevice device;
    private final Set<String> schemes;

    public ApachePathParser(ApacheVfsDriver driver) {
        device = driver.getDevice();
        schemes = Collections.unmodifiableSet(//
                Collections.toHashSet(//
                        driver.getFileSystemManager().getSchemes()));
    }

    @Override
    public boolean accepts(String protocol) {
        return schemes.contains(protocol);
    }

    @Override
    public IPath parse(String path)
            throws BadPathException {
        String uri = path;
        return device.parse(uri);
    }

}
