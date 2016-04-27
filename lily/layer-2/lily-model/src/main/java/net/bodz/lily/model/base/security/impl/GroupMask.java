package net.bodz.lily.model.base.security.impl;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.model.base.CoObjectMask;

/**
 * @see net.bodz.lily.model.base.security.Group
 */
public class GroupMask
        extends CoObjectMask {

    @Override
    public void readObject(IVariantMap<String> map)
            throws ParseException {
        super.readObject(map);
    }

}
