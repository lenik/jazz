package net.bodz.bas.db.ibatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public abstract class AbstractMapperProxy
        implements
            InvocationHandler {

    static final Logger logger = LoggerFactory.getLogger(PrivateSessionMapperProxy.class);

    Object proxyThis;

    Class<?> mapperClass;
    MapperHelper<?, ?> helper;

    public <mapper_t extends IGenericMapper<T, M>, T, M> //
    AbstractMapperProxy(Class<?> mapperClass) {
        this.mapperClass = mapperClass;
        Class<?>[] args = TypeParam.infer(mapperClass, IGenericMapper.class);
        this.helper = new MapperHelper<>(args[0], args[1]);
    }

    @Override
    public final Object invoke(Object obj, Method method, Object[] args)
            throws Throwable {
        if (method.isAnnotationPresent(Helper.class))
            return method.invoke(helper, args);

//        if (method.isDefault())
//            return method.invoke(proxyThis, args);

        return invokeMapperMethod(obj, method, args);
    }

    protected abstract Object invokeMapperMethod(Object obj, Method method, Object[] args)
            throws Throwable;

}