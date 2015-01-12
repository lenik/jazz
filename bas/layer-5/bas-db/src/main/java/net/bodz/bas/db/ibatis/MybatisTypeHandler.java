package net.bodz.bas.db.ibatis;

import org.apache.ibatis.type.BaseTypeHandler;

import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public abstract class MybatisTypeHandler<T>
        extends BaseTypeHandler<T> {

}
