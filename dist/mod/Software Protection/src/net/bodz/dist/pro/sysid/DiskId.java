package net.bodz.dist.pro.sysid;

import net.bodz.bas.lang.err.SystemException;

public class DiskId extends _SysIdProvider {

    private final int diskIndex;

    public DiskId(int diskIndex) {
        super("-"); //$NON-NLS-1$
        this.diskIndex = diskIndex;
    }

    @Override
    public byte[] getId() throws SystemException {
        return null;
    }

}
