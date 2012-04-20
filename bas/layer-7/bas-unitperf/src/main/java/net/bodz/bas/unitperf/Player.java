package net.bodz.bas.unitperf;

import java.lang.reflect.Method;

import net.bodz.bas.trait.Traits;
import net.bodz.bas.traits.IInstanceStore;

public class Player {

    void testMethodOrtho(Method method, int orthoLevel, int orthoIndex) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        int count = parameterTypes.length;
        IInstanceStore<?>[] parameterStores = new IInstanceStore<?>[count];

        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> parameterType = parameterTypes[i];
            IInstanceStore<?> instanceStore = Traits.getTrait(parameterType, IInstanceStore.class);
            parameterStores[i] = instanceStore;
        }

    }

}
