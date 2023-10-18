package net.bodz.bas.db.ibatis;

import java.lang.reflect.Method;

import org.apache.ibatis.session.SqlSession;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class SharedSessionMapperProxy
        extends AbstractMapperProxy {

    static final Logger logger = LoggerFactory.getLogger(SharedSessionMapperProxy.class);

    SqlSession session;

    public SharedSessionMapperProxy(SqlSession session, Class<?> mapperClass) {
        super(mapperClass);
        this.session = session;
    }

    @Override
    protected Object invokeMapperMethod(Object obj, Method method, Object[] args)
            throws Throwable {
        Object result;

        Object proxy = session.getMapper(mapperClass);

        long begin = System.nanoTime();

        try {
            result = method.invoke(proxy, args);

            if (method.isAnnotationPresent(Commit.class)) {
                session.commit();
                // session.clearCache();
            }
        } finally {
            if (timing && begin != -1) {
                long d = System.nanoTime() - begin;
                showDuration(d, method, args);
            }
        }
        return result;
    }

}
