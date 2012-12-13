package net.bodz.bas.c.reflect.query;

import java.lang.reflect.Method;

import net.bodz.bas.t.iterator.immed.AbstractMitorx;
import net.bodz.bas.t.iterator.immed.Mitorx;

public class PublicMethods
        extends MethodSelection {

    private final Class<?> clazz;

    public PublicMethods(Class<?> clazz) {
        if (clazz == null)
            throw new NullPointerException("clazz");
        this.clazz = clazz;
    }

    class Iter
            extends AbstractMitorx<Method, RuntimeException> {

        Method[] methods = clazz.getMethods();
        int currentIndex = -1;

        @Override
        public Method _next()
                throws RuntimeException {
            while (++currentIndex < methods.length) {
                Method m = methods[currentIndex];
                if (modifierTest != (modifierMask & m.getModifiers()))
                    continue;
                if (namePredicate != null && !namePredicate.evaluate(m.getName()))
                    continue;
                if (parametersPredicate != null && !parametersPredicate.evaluate(m.getParameterTypes()))
                    continue;
                return m;
            }
            return end();
        }
    }

    @Override
    public Mitorx<? extends Method, ? extends RuntimeException> iterator(boolean allowOverlap)
            throws RuntimeException {
        return new Iter();
    }

}
