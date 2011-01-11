package net.bodz.bas.reflect.query;

import java.lang.reflect.Constructor;

import net.bodz.bas.collection.iterator.AbstractImmediateIteratorX;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;

public class DeclaredConstructors
        extends ConstructorSelection {

    private final Class<?> clazz;

    public DeclaredConstructors(Class<?> clazz) {
        if (clazz == null)
            throw new NullPointerException("clazz");
        this.clazz = clazz;
    }

    class Iter
            extends AbstractImmediateIteratorX<Constructor<?>, RuntimeException> {

        Constructor<?>[] ctors = clazz.getDeclaredConstructors();
        int currentIndex = -1;

        @Override
        public Constructor<?> next()
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
    public ImmediateIteratorX<? extends Constructor<?>, ? extends RuntimeException> iterator(boolean allowOverlap)
            throws RuntimeException {
        return new Iter();
    }

}
