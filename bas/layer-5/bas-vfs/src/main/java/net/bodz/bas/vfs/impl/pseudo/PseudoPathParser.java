package net.bodz.bas.vfs.impl.pseudo;

import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.MultiSessionPathParser;

/**
 * pseudo://session/local-path
 */
public class PseudoPathParser
        extends MultiSessionPathParser {

    private PseudoVfsDriver driver;

    public PseudoPathParser(PseudoVfsDriver driver) {
        if (driver == null)
            throw new NullPointerException("driver");
        this.driver = driver;
    }

    @Override
    public IPath parse(String session, String path)
            throws BadPathException {
        PseudoVfsDevice device = driver.getDevice(session);
        return device.parse(path);
    }

}
