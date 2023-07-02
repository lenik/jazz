package net.bodz.lily.security.dao;

import net.bodz.bas.c.string.StringUtil;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.lily.model.base.CoObjectMask;

public class CoPrincipalMask
        extends CoObjectMask {

    IntegerRange idRange;

    String name;

    public IntegerRange getIdRange() {
        return idRange;
    }

    public void setIdRange(IntegerRange idRange) {
        this.idRange = idRange;
    }

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
