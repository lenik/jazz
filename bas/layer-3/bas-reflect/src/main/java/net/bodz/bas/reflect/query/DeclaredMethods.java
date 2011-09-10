package net.bodz.bas.reflect.query;

import java.lang.reflect.Method;

import net.bodz.bas.collection.iterator.AbstractIteratorMX;
import net.bodz.bas.collection.iterator.IteratorMX;

/**
 * @test {@link DeclaredMethodsTest}
 */
public class DeclaredMethods
        extends MethodSelection {

    private final Class<?> clazz;

    protected Class<?> stopClass;

    /**
     * @throws NullPointerException
     *             If <code>clazz</code> is <code>null</code>.
     */
    public DeclaredMethods(Class<?> clazz) {
        if (clazz == null)
            throw new NullPointerException("clazz");
        this.clazz = clazz;
    }

    /**
     * @param stopClass
     *            <code>null</code> If not constrained.
     */
    public DeclaredMethods stopAt(Class<?> stopClass) {
        this.stopClass = stopClass;
        return this;
    }

    class Iter
            extends AbstractIteratorMX<Method, RuntimeException> {

        Class<?> currentClass;
        Method[] declaredMethodsOfCurrentClass;
        int currentIndex;

        public Iter() {
            if (stopClass == null || !clazz.isAssignableFrom(stopClass)) {
                currentClass = clazz;
                declaredMethodsOfCurrentClass = currentClass.getDeclaredMethods();
                currentIndex = -1;
            }
        }

        @Override
        public Method _next()
                throws RuntimeException {
            while (currentClass != null) {
                while (++currentIndex < declaredMethodsOfCurrentClass.length) {
                    Method m = declaredMethodsOfCurrentClass[currentIndex];
                    if (modifierTest != (modifierMask & m.getModifiers()))
                        continue;
                    if (namePredicate != null && !namePredicate.test(m.getName()))
                        continue;
                    if (parametersPredicate != null && !parametersPredicate.test(m.getParameterTypes()))
                        continue;
                    return m;
                }
                currentClass = currentClass.getSuperclass();
                if (currentClass == null)
                    break;
                if (stopClass != null && currentClass.isAssignableFrom(stopClass))
                    break;
                declaredMethodsOfCurrentClass = currentClass.getDeclaredMethods();
                currentIndex = -1;
            }
            return end();
        }

    }

    @Override
    public IteratorMX<? extends Method, ? extends RuntimeException> iterator(boolean allowOverlap)
            throws RuntimeException {
        return new Iter();
    }

}
