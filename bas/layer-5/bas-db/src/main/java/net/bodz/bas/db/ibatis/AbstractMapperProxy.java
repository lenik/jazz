package net.bodz.bas.db.ibatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.util.DurationFormat;
import net.bodz.bas.util.JavaLang;

public abstract class AbstractMapperProxy
        implements
            InvocationHandler {

    static final Logger logger = LoggerFactory.getLogger(PrivateSessionMapperProxy.class);

    Object proxyThis;

    Class<?> mapperClass;
    MapperHelper<?> helper;

    boolean timing = true;
    boolean showCallInfo = true;
    int maxLen = 30;

    public <mapper_t extends IGenericMapper<T>, T> //
    AbstractMapperProxy(Class<?> mapperClass) {
        this.mapperClass = mapperClass;
        Class<?>[] args = TypeParam.infer(mapperClass, IGenericMapper.class);
        this.helper = new MapperHelper<>(args[0]);
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

    void showDuration(long d, Method method, Object[] args) {
        String duration = DurationFormat.formatNanoDuration(d);

        StringBuilder sb = new StringBuilder();
        sb.append("time: ");
        sb.append(duration);

        if (showCallInfo) {
            sb.append(" in ");
            sb.append(mapperClass.getSimpleName());
            sb.append(".");
            sb.append(method.getName());
            sb.append("(");
            if (args != null)
                for (int i = 0; i < args.length; i++) {
                    if (i != 0)
                        sb.append(", ");
                    Object o = args[i];
                    String s = JavaLang.toJavaLiteral(o, maxLen);
                    sb.append(s);
                }
            sb.append(")");
        }
        logger.info(sb);
    }

}