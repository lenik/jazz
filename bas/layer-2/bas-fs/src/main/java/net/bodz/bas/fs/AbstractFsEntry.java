package net.bodz.bas.fs;

import net.bodz.bas.fs.path.IPath;
import net.bodz.bas.type.traits.Attributes;

public abstract class AbstractFsEntry
        extends Attributes
        implements IFsEntry {

    private final IFileContainer container;
    private final IPath path;
    private final String baseName;

    private boolean createParents;

    public AbstractFsEntry(IPath path) {
        this(null, path);
    }

    public AbstractFsEntry(IFileContainer fileContainer, IPath path) {
        if (fileContainer == null)
            throw new NullPointerException("fileContainer");
        if (path == null)
            throw new NullPointerException("path");
        this.container = fileContainer;
        this.path = path;
        this.baseName = path.getBaseName();
        assert baseName != null;
    }

    @Override
    public abstract IFsEntry clone();

    @Override
    public IFileContainer getContainer() {
        return container;
    }

    @Override
    public IPath getPath() {
        return path;
    }

    @Override
    public String getName() {
        return baseName;
    }

    @Override
    public String getExtension(boolean withDot, int maxWords) {
        if (baseName == null)
            return null;
        int pos = baseName.length();
        while (maxWords-- > 0) {
            int dot = baseName.lastIndexOf('.', pos);
            if (dot == -1)
                break;
            pos = dot;
        }
        if (!withDot)
            pos++;
        if (pos < baseName.length())
            baseName.substring(pos);
        return "";
    }

    @Override
    public boolean isExisted() {
        return exists() == Boolean.TRUE;
    }

    @Override
    public boolean isNotExisted() {
        return exists() == Boolean.FALSE;
    }

    @Override
    public boolean getCreateParentsMode() {
        return createParents;
    }

    @Override
    public void setCreateParentsMode(boolean createParents) {
        this.createParents = createParents;
    }

    @Override
    public String toString() {
        return getPath().toString();
    }

}
