package net.bodz.bas.fs;

import net.bodz.bas.type.traits.impl.Attributes;

public abstract class AbstractEntry
        extends Attributes
        implements IEntry {

    private final IFolder parentFolder;
    private final String name;
    private boolean createParents;

    public AbstractEntry(IFolder parentFolder, String name) {
        this.parentFolder = parentFolder;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getExtension(boolean withDot, int maxWords) {
        if (name == null)
            return null;
        int pos = name.length();
        while (maxWords-- > 0) {
            int dot = name.lastIndexOf('.', pos);
            if (dot == -1)
                break;
            pos = dot;
        }
        if (!withDot)
            pos++;
        if (pos < name.length())
            name.substring(pos);
        return "";
    }

    @Override
    public IFolder getParentFolder() {
        return parentFolder;
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

    protected <T extends AbstractEntry> T clone(T o) {
        o.createParents = createParents;
        return o;
    }

}
