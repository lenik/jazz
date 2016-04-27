package net.bodz.bas.db.ibatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class PrivateSessionMapperProxy
        implements InvocationHandler {

    SqlSessionFactory sqlSessionFactory;
    Class<?> mapperClass;

    public PrivateSessionMapperProxy(SqlSessionFactory sqlSessionFactory, Class<?> mapperClass) {
        this.sqlSessionFactory = sqlSessionFactory;
        this.mapperClass = mapperClass;
    }

    @Override
    public Object invoke(Object obj, Method method, Object[] args)
            throws Throwable {
        Object result;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            Object proxy = session.getMapper(mapperClass);
            result = method.invoke(proxy, args);
            session.commit();
        } finally {
            session.close();
        }
        return result;
    }

}
