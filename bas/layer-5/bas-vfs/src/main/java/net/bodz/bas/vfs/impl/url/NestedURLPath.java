package net.bodz.bas.vfs.impl.url;

import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.PathFormats;

public class NestedURLPath
        extends URLPath {

    private static final long serialVersionUID = 1L;

    final IPath nestedPath;

    public NestedURLPath(String scheme, IPath nestedPath, String localPath) {
        super(scheme, localPath);
        if (nestedPath == null)
            throw new NullPointerException("nestedPath");
        this.nestedPath = nestedPath;
    }

    public NestedURLPath(String scheme, IPath nestedPath, String[] entries) {
        super(scheme, entries);
        if (nestedPath == null)
            throw new NullPointerException("nestedPath");
        this.nestedPath = nestedPath;
    }

    @Override
    protected NestedURLPath createLocal(String[] entries)
            throws BadPathException {
        return new NestedURLPath(scheme, nestedPath, entries);
    }

    @Override
    protected NestedURLPath createLocal(String localPath)
            throws BadPathException {
        return new NestedURLPath(scheme, nestedPath, localPath);
    }

    @Override
    public String getDeviceSpec() {
        String nestedStr = nestedPath.format(PathFormats.REPR);
        return nestedStr;
    }

    @Override
    public String getDeviceSpecSeparator() {
        return "!/";
    }

    @Override
    public IPath getParentLayer() {
        return nestedPath;
    }

}
