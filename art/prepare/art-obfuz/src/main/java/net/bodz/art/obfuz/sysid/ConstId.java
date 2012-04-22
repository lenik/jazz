package net.bodz.art.obfuz.sysid;

import net.bodz.bas.err.SystemException;

public class ConstId
        extends AbstractSysIdProvider {

    final byte[] id;

    public ConstId(byte[] id) {
        super("-"); //$NON-NLS-1$
        this.id = id;
    }

    @Override
    public byte[] getId()
            throws SystemException {
        return id;
    }

}
