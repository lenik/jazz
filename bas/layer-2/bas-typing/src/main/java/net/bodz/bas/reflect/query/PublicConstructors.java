package net.bodz.bas.reflect.query;

import java.lang.reflect.Constructor;

import net.bodz.bas.util.iter.AbstractMitorx;
import net.bodz.bas.util.iter.Mitorx;

public class PublicConstructors
        extends ConstructorSelection {

    private final Class<?> clazz;

    public PublicConstructors(Class<?> clazz) {
        if (clazz == null)
            throw new NullPointerException("clazz");
        this.clazz = clazz;
    }

    class Iter
            extends AbstractMitorx<Constructor<?>, RuntimeException> {

        Constructor<?>[] ctors = clazz.getConstructors();
        int currentIndex = -1;

        @Override
        public Constructor<?> _next()
                throws RuntimeException {
            while (++currentIndex < ctors.length) {
                Constructor<?> ctor = ctors[currentIndex];
                if (modifierTest != (modifierMask & ctor.getModifiers()))
                    continue;
                if (parametersPredicate != null && !parametersPredicate.evaluate(ctor.getParameterTypes()))
                    continue;
                return ctor;
            }
            return end();
        }
    }

    @Override
    public Mitorx<? extends Constructor<?>, ? extends RuntimeException> iterator(boolean allowOverlap)
            throws RuntimeException {
        return new Iter();
    }

}
