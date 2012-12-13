package net.bodz.bas.vfs.impl.pseudo;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.ProtocolPath;

public class PseudoPath
        extends ProtocolPath {

    private static final long serialVersionUID = 1L;
    public static final String SCOPE_SEPARATOR = ":";

    private String scope;
    private PseudoFile file;

    public PseudoPath(String protocol, String scope, String localPath, PseudoFile file) {
        super(protocol, localPath);
        if (scope == null)
            throw new NullPointerException("scope");
        if (file == null)
            throw new NullPointerException("file");
        this.scope = scope;
        this.file = file;
    }

    @Override
    public String getDeviceSpec() {
        return scope;
    }

    @Override
    public String getDeviceSpecSeparator() {
        return SCOPE_SEPARATOR;
    }

    @Override
    protected PseudoPath createLocal(String localPath)
            throws BadPathException {
        if (Nullables.equals(getLocalPath(), localPath))
            return this;
        else {
            PseudoVfsDriver driver = PseudoVfsDriver.getInstance();
            PseudoVfsDevice device = driver.getDevice(scope);
            assert device != null;
            return device.parse(localPath);
        }
    }

    @Override
    protected PseudoPath createLocal(String[] entries)
            throws BadPathException {
        String localPath = StringArray.join(SEPARATOR, entries);
        return createLocal(localPath);
    }

    @Override
    public IFile resolve()
            throws FileResolveException {
        return file;
    }

}
