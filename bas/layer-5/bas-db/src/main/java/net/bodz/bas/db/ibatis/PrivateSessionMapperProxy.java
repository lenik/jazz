package net.bodz.bas.db.ibatis;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class PrivateSessionMapperProxy
        extends AbstractMapperProxy {

    static final Logger logger = LoggerFactory.getLogger(PrivateSessionMapperProxy.class);

    SqlSessionFactory sqlSessionFactory;

    public PrivateSessionMapperProxy(SqlSessionFactory sqlSessionFactory, Class<?> mapperClass) {
        super(mapperClass);
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    protected Object invokeMapperMethod(Object obj, Method method, Object[] args)
            throws Throwable {
        Object result;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            Object proxy = session.getMapper(mapperClass);
            result = method.invoke(proxy, args);
            session.commit();
        } catch (InvocationTargetException e) {
            Throwable te = e.getTargetException();
            logger.errorf("Failed to execute %s.%s: %s", //
                    mapperClass.getSimpleName(), method.getName(), te.getMessage());
            throw te;
        } finally {
            session.close();
        }
        return result;
    }

}