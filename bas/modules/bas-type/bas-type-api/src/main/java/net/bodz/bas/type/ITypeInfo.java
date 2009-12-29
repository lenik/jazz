package net.bodz.bas.type;

import net.bodz.bas.lang.IQueryable;

public interface ITypeInfo
        extends IQueryable {

    @Override
    Object query(String infoId);

    @Override
    <T> T query(Class<T> infoClass);

}
