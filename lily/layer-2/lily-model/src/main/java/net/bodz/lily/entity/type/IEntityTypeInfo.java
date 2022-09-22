package net.bodz.lily.entity.type;

public interface IEntityTypeInfo {

    Class<?> getEntityClass();

    Class<?> getIdClass();

    Class<?> getMapperClass();

    Class<?> getCrtieriaClass();

}
