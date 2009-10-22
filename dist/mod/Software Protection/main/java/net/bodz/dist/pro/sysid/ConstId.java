package net.bodz.dist.pro.sysid;

import net.bodz.bas.lang.err.SystemException;

public class ConstId extends _SysIdProvider {

    final byte[] id;

    public ConstId(byte[] id) {
        super("-"); //$NON-NLS-1$
        this.id = id;
    }

    @Override
    public byte[] getId() throws SystemException {
        return id;
    }

}
