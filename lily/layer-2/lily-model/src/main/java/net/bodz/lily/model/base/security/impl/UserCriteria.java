package net.bodz.lily.model.base.security.impl;

import net.bodz.bas.err.ParseException;

import net.bodz.lily.model.base.CoObjectCriteria;
import net.bodz.lily.model.sea.QVariantMap;

/**
 * @see net.bodz.lily.model.base.security.User
 */
public class UserCriteria
        extends CoObjectCriteria {

    public String password;

    @Override
    protected void populate(QVariantMap<String> map)
            throws ParseException {
        super.populate(map);
        password = map.getString("passwd", password);
    }

}
