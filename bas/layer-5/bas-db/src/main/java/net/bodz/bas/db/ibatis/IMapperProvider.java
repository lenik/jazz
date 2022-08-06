package net.bodz.bas.db.ibatis;

import org.apache.ibatis.session.SqlSession;

public interface IMapperProvider {

    String ATTRIBUTE_KEY = IMapperProvider.class.getName();

    /**
     * @see SqlSession#getMapper(Class)
     */
    default <M> M getMapper(Class<M> mapperClass) {
        return getMapper(mapperClass, true);
    }

    <M> M getMapper(Class<M> mapperClass, boolean batch);

    class fn {

    }

}
