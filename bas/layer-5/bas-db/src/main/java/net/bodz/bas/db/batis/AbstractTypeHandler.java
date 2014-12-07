package net.bodz.bas.db.batis;

import org.apache.ibatis.type.BaseTypeHandler;

import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public abstract class AbstractTypeHandler<T>
        extends BaseTypeHandler<T> {

}
