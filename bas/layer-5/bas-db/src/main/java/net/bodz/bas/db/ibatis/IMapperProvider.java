package net.bodz.bas.db.ibatis;

import org.apache.ibatis.session.SqlSession;

public interface IMapperProvider {

    String ATTRIBUTE_KEY = IMapperProvider.class.getName();

    /**
     * @see SqlSession#getMapper(Class)
     */
    <M extends IMapper> M getMapper(Class<M> mapperClass);

    <M extends IMapper> M getMapper(Class<M> mapperClass, boolean batch);

    <M extends IMapper> M getMapper(Class<M> mapperClass, SqlSession session);

    class fn {

    }

}
