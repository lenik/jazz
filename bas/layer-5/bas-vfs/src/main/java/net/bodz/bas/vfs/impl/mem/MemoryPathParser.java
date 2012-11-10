package net.bodz.bas.vfs.impl.mem;

import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.MultiSessionPathParser;

/**
 * mem://session/local-path
 */
public class MemoryPathParser
        extends MultiSessionPathParser {

    private MemoryVfsDriver driver;

    public MemoryPathParser(MemoryVfsDriver driver) {
        if (driver == null)
            throw new NullPointerException("driver");
        this.driver = driver;
    }

    @Override
    public IPath parse(String session, String path)
            throws BadPathException {
        MemoryVfsDevice device = driver.getDevice(session);
        return device.parse(path);
    }

}
