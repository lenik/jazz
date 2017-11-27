package net.bodz.lily.model.base.security.impl;

import net.bodz.bas.c.string.StringUtil;
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

}
