package net.bodz.dist.pro.sysid;

import net.bodz.bas.lang.err.SystemException;

public class CpuId extends _SysIdProvider {

    private final int cpuIndex;

    public CpuId(int cpuIndex) {
        super("-");
        this.cpuIndex = cpuIndex;
    }

    @Override
    public byte[] getId() throws SystemException {
        return null;
    }

}
