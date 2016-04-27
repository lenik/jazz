package net.bodz.bas.db.ibatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.apache.ibatis.session.SqlSession;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class SharedSessionMapperProxy
        implements InvocationHandler {

    static final Logger logger = LoggerFactory.getLogger(SharedSessionMapperProxy.class);

    SqlSession session;
    Class<?> mapperClass;

    public SharedSessionMapperProxy(SqlSession session, Class<?> mapperClass) {
        this.session = session;
        this.mapperClass = mapperClass;
    }

    @Override
    public Object invoke(Object obj, Method method, Object[] args)
            throws Throwable {
        Object result;

        Object proxy = session.getMapper(mapperClass);
        result = method.invoke(proxy, args);

        if (method.isAnnotationPresent(Commit.class)) {
            session.commit();
            // session.clearCache();
        }

        return result;
    }
}
