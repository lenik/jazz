package net.bodz.lily.entity;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.bean.Internal;

public interface IIdentity {

    @Internal
    IIdentityTypeInfo getType();

    void parse(String... columns)
            throws ParseException;

}
