package net.bodz.bas.unitperf;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.bodz.bas.typeinfo.IInstanceStore;
import net.bodz.bas.typeinfo.impl.StoreClass;
import net.bodz.bas.typeinfo.impl.TypeInfoUtil;

public class Player {

    private static <A extends Annotation> A findAnnotation(Annotation[] annotations, Class<A> annotatypeType) {
        for (Annotation a : annotations)
            if (annotatypeType.equals(a.annotationType()))
                return annotatypeType.cast(a);
        return null;
    }

    void testMethodOrtho(Method method, int orthoLevel, int orthoIndex) {
        Annotation[][] allParameterAnnotations = method.getParameterAnnotations();
        Class<?>[] parameterTypes = method.getParameterTypes();
        int count = parameterTypes.length;
        IInstanceStore<?>[] parameterStores = new IInstanceStore<?>[count];

        for (int i = 0; i < parameterTypes.length; i++) {
            Annotation[] parameterAnnotations = allParameterAnnotations[i];
            Class<?> parameterType = parameterTypes[i];
            StoreClass storeClassAnnotation = findAnnotation(parameterAnnotations, StoreClass.class);
            IInstanceStore<?> instanceStore = TypeInfoUtil.findInstanceStore(parameterType, storeClassAnnotation);
            parameterStores[i] = instanceStore;
        }

    }

}
