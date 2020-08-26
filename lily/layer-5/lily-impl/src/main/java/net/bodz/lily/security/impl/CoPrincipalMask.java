package net.bodz.lily.security.impl;

import net.bodz.bas.c.string.StringUtil;
import net.bodz.lily.model.base.CoObjectMask;

public class CoPrincipalMask
        extends CoObjectMask {

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName1() {
        String s = getName();
        if (s != null)
            s = StringUtil.enc1(s);
        return s;
    }

}
