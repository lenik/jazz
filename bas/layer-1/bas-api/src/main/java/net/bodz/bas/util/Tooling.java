package net.bodz.bas.util;

import java.lang.reflect.Constructor;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.model.IFactory;

public final class Tooling {

    final Object hostObject;

    public Tooling(Object hostObject) {
        if (hostObject == null)
            throw new NullPointerException("hostObject");
        this.hostObject = hostObject;
    }

    public static Tooling on(Object hostObject) {
        return new Tooling(hostObject);
    }

    public <F extends IFactory<? extends T>, T> T _for(F factory)
            throws ToolsInstantiationException {
        if (factory == null)
            throw new NullPointerException("factory");
        T tools;
        try {
            tools = factory.create();
        } catch (CreateException e) {
            throw new ToolsInstantiationException(e.getMessage(), e);
        }
        return tools;
    }

    public <T> T _for(Class<T> toolsType) {
        Class<?> hostClass = hostObject.getClass();

        Constructor<?> minctor = null;
        int mindist = Integer.MAX_VALUE;

        for (Constructor<?> ctor : toolsType.getConstructors()) {
            Class<?>[] parameterTypes = ctor.getParameterTypes();
            if (parameterTypes.length != 1)
                continue;
            Class<?> parameterType = parameterTypes[0];
            int dist = dist(hostClass, parameterType);
            if (dist < mindist) {
                mindist = dist;
                minctor = ctor;
            }
        }
    }

    static int dist(Class<?> sub, Class<?> base) {
        if (sub == base)
            return 0;

        int minDist = Integer.MAX_VALUE;

        if (base.isInterface()) {
            Class<?>[] ifaces = sub.getInterfaces();
            if (ifaces.length > 0) {
                int minidist = dist(ifaces[0], base);
                for (int i = 1; i < ifaces.length; i++) {
                    int idist = dist(ifaces[i], base);
                    if (idist == 0)
                        return 1;
                    if (idist < minidist)
                        minidist = idist;
                }
                minDist = minidist;
            }
        }

        Class<?> superclass = sub.getSuperclass();
        if (superclass != null) {
            int superdist = dist(superclass, base);
            if (superdist != -1)
                return superdist + 1;
        }

    }

    interface A1 {
    }

    interface A2 {
    }

    interface B
            extends A1, A2 {
    }

    public static void main(String[] args) {

    }
}