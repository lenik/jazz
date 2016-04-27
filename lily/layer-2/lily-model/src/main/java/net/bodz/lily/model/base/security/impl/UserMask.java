package net.bodz.lily.model.base.security.impl;

import net.bodz.bas.c.string.StringUtil;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.model.base.CoObjectMask;

/**
 * @see net.bodz.lily.model.base.security.User
 */
public class UserMask
        extends CoObjectMask {

    public String password;

    public String getCodeName1() {
        String s = getCodeName();
        if (s != null)
            s = StringUtil.enc1(s);
        return s;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws ParseException {
        super.readObject(map);
        password = map.getString("passwd", password);
    }

}
