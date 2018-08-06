package net.bodz.lily.security.impl;

import net.bodz.bas.c.string.StringUtil;
import net.bodz.lily.model.base.CoObjectMask;

public class CoPrincipalMask
        extends CoObjectMask {

    public String getName() {
        return super.getCodeName();
    }

    public void setName(String loginName) {
        super.setCodeName(loginName);
    }

    public String getName1() {
        String s = getName();
        if (s != null)
            s = StringUtil.enc1(s);
        return s;
    }

}
