package net.bodz.lily.entity.manager;

import net.bodz.lily.entity.type.IEntityTypeInfo;

public interface IEntityCommandBuilder {

    IEntityCommandBuilder entityType(Class<?> entityClass);

    IEntityCommandBuilder entityType(IEntityTypeInfo typeInfo);

    boolean checkValid();

    IEntityCommand build();

}
