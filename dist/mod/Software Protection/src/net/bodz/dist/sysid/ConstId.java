package net.bodz.dist.sysid;

import net.bodz.bas.lang.err.SystemException;

public class ConstId extends _SysIdProvider {

    final byte[] id;

    public ConstId(byte[] id) {
        super("-");
        this.id = id;
    }

    @Override
    public byte[] getId() throws SystemException {
        return id;
    }

}
