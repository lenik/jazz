package net.bodz.bas.bean;

import java.lang.reflect.Method;
import java.util.Map;

public interface IReflectSession {

    // MethodKey
    Map<Method, Object> listMethods(Class<?> clazz);

}
