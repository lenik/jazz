package net.bodz.bas.reflect.query;

import java.lang.reflect.Method;

import net.bodz.bas.collection.iterator.AbstractImmediateIteratorX;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;

/**
 * @test {@link PublicMethodsTest}
 */
public class PublicMethods
        extends MethodSelection {

    private final Class<?> clazz;

    public PublicMethods(Class<?> clazz) {
        if (clazz == null)
            throw new NullPointerException("clazz");
        this.clazz = clazz;
    }

    class Iter
            extends AbstractImmediateIteratorX<Method, RuntimeException> {

        Method[] methods = clazz.getMethods();
        int currentIndex = -1;

        @Override
        public Method next()
                throws RuntimeException {
            while (++currentIndex < methods.length) {
                Method m = methods[currentIndex];
                if (namePredicate != null && !namePredicate.test(m.getName()))
                    continue;
                if (parametersPredicate != null && parametersPredicate.test(m.getParameterTypes()))
                    continue;
                return m;
            }
            return end();
        }
    }

    @Override
    public ImmediateIteratorX<? extends Method, ? extends RuntimeException> iterator(boolean allowOverlap)
            throws RuntimeException {
        return new Iter();
    }

}
