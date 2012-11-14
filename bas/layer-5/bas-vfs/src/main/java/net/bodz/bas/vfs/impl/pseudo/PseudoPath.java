package net.bodz.bas.vfs.impl.pseudo;

import net.bodz.bas.util.Nullables;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.ProtocolPath;

public class PseudoPath
        extends ProtocolPath {

    private static final long serialVersionUID = 1L;

    private String session;
    private PseudoFile file;

    public PseudoPath(String protocol, String session, String localPath, PseudoFile file) {
        super(protocol, localPath);
        if (session == null)
            throw new NullPointerException("session");
        if (file == null)
            throw new NullPointerException("file");
        this.session = session;
        this.file = file;
    }

    @Override
    public String getDeviceName() {
        return session;
    }

    @Override
    protected IPath createLocal(String[] entries)
            throws BadPathException {
        if (Nullables.equals(getLocalPath(), entries))
            return this;
        else {
            PseudoVfsDriver driver = PseudoVfsDriver.getInstance();
            PseudoVfsDevice device = driver.getDevice(session);
            assert device != null;
            return device.parse(entries);
        }
    }

    @Override
    public IFile resolve()
            throws FileResolveException {
        return file;
    }

}
