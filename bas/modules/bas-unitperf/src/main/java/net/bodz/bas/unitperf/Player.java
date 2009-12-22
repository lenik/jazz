package net.bodz.bas.unitperf;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.bodz.bas.typeinfo.InstanceStore;
import net.bodz.bas.typeinfo.util.StoreClass;
import net.bodz.bas.typeinfo.util.TypeInfoUtil;

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
        InstanceStore<?>[] parameterStores = new InstanceStore<?>[count];

        for (int i = 0; i < parameterTypes.length; i++) {
            Annotation[] parameterAnnotations = allParameterAnnotations[i];
            Class<?> parameterType = parameterTypes[i];
            StoreClass storeClassAnnotation = findAnnotation(parameterAnnotations, StoreClass.class);
            InstanceStore<?> instanceStore = TypeInfoUtil.findInstanceStore(parameterType, storeClassAnnotation);
            parameterStores[i] = instanceStore;
        }

    }

}
