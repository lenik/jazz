package net.bodz.bas.unitperf;

import java.lang.reflect.Method;

import net.bodz.bas.mf.MdaFeatures;
import net.bodz.bas.mf.std.IInstanceStore;

public class Player {

    void testMethodOrtho(Method method, int orthoLevel, int orthoIndex) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        int count = parameterTypes.length;
        IInstanceStore<?>[] parameterStores = new IInstanceStore<?>[count];

        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> parameterType = parameterTypes[i];
            IInstanceStore<?> instanceStore = MdaFeatures.getMdaFeature(parameterType, IInstanceStore.class);
            parameterStores[i] = instanceStore;
        }

    }

}
