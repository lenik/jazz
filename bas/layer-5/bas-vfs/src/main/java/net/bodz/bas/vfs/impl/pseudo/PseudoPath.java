package net.bodz.bas.vfs.impl.pseudo;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.FileResolveOptions;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.MultiEntryPath;

public class PseudoPath
        extends MultiEntryPath {

    private static final long serialVersionUID = 1L;
    public static final String SCOPE_SEPARATOR = ":/";

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

    @NotNull
    @Override
    protected PseudoPath createLocal(String localPath)
            throws BadPathException {
        if (Nullables.equals(joinEntries(), localPath))
            return this;
        else {
            PseudoVfsDriver driver = PseudoVfsDriver.getInstance();
            PseudoVfsDevice device = driver.getDevice(scope);
            assert device != null;
            return device.parseLocalPath(localPath);
        }
    }

    @NotNull
    @Override
    protected PseudoPath createLocal(String[] entries, boolean entered)
            throws BadPathException {
        String localPath = StringArray.join(SEPARATOR, entries);
        if (entered)
            localPath += SEPARATOR;
        return createLocal(localPath);
    }

    @Override
    public IFile toFile(FileResolveOptions options)
            throws FileResolveException {
        // XXX follow-symlink?
        return file;
    }

}
