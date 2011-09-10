package net.bodz.bas.reflect.query;

import java.lang.reflect.Constructor;

import net.bodz.bas.collection.iterator.AbstractIteratorMX;
import net.bodz.bas.collection.iterator.IteratorMX;

public class PublicConstructors
        extends ConstructorSelection {

    private final Class<?> clazz;

    public PublicConstructors(Class<?> clazz) {
        if (clazz == null)
            throw new NullPointerException("clazz");
        this.clazz = clazz;
    }

    class Iter
            extends AbstractIteratorMX<Constructor<?>, RuntimeException> {

        Constructor<?>[] ctors = clazz.getConstructors();
        int currentIndex = -1;

        @Override
        public Constructor<?> _next()
                throws RuntimeException {
            while (++currentIndex < ctors.length) {
                Constructor<?> ctor = ctors[currentIndex];
                if (modifierTest != (modifierMask & ctor.getModifiers()))
                    continue;
                if (parametersPredicate != null && !parametersPredicate.test(ctor.getParameterTypes()))
                    continue;
                return ctor;
            }
            return end();
        }
    }

    @Override
    public IteratorMX<? extends Constructor<?>, ? extends RuntimeException> iterator(boolean allowOverlap)
            throws RuntimeException {
        return new Iter();
    }

}
