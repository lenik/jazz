package net.bodz.bas.aspect.typeinfo;

import net.bodz.bas.api.types.Queryable;

public interface TypeInfo
        extends Queryable {

    @Override
    Object query(String infoId);

//    @Override
//    <T> T query(Class<T> infoClass);


}
