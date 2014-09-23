package net.bodz.bas.db.batis;

import org.apache.ibatis.session.SqlSession;

public interface IMapperProvider {

    String ATTRIBUTE_KEY = IMapperProvider.class.getName();

    /**
     * @see SqlSession#getMapper(Class)
     */
    <T extends IMapper> T getMapper(Class<T> mapperClass);

}
