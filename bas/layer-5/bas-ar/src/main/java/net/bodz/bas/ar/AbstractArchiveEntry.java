package net.bodz.bas.ar;

import net.bodz.bas.io.res.AbstractStreamResource;

public abstract class AbstractArchiveEntry
        extends AbstractStreamResource
        implements IArchiveEntry {

    @Override
    public long getCreatedTime() {
        return getModifiedTime();
    }

    @Override
    public int getMode() {
        return defaultMode;
    }

    @Override
    public String getOwner() {
        return null;
    }

    @Override
    public String getGroup() {
        return null;
    }

    @Override
    public String getComment() {
        return null;
    }

}
