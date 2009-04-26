package net.bodz.dist.sysid;

import net.bodz.bas.lang.err.SystemException;

public class DiskId extends _SysIdProvider {

    private final int diskIndex;

    public DiskId(int diskIndex) {
        super("-");
        this.diskIndex = diskIndex;
    }

    @Override
    public byte[] getId() throws SystemException {
        return null;
    }

}
