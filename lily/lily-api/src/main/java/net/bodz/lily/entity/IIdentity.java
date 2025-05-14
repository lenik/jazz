package net.bodz.lily.entity;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.bean.Internal;

public interface IIdentity {

    @Internal
    IIdentityTypeInfo getType();

    default void parse(String... columns)
            throws ParseException {
        IIdentityTypeInfo type = getType();
        type.getColumnNames();
    }

}
