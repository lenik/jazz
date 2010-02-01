package net.bodz.bas.reflect.query;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import net.bodz.bas.collection.iterator.AbstractImmediateIteratorX;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;

/**
 * @test {@link DeclaredMethodsTest}
 */
public class DeclaredMethods
        extends MethodSelection {

    private final Class<?> clazz;

    protected Class<?> stopClass;
    protected int modifierMask;
    protected int modifier;

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

    static final int accessModifierMask = Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE;

    public DeclaredMethods withAccess(boolean includePublic, boolean includeProtected, boolean includePrivate) {
        modifierMask |= accessModifierMask;
        modifier &= ~accessModifierMask;
        if (includePublic)
            modifier |= Modifier.PUBLIC;
        if (includeProtected)
            modifier |= Modifier.PROTECTED;
        if (includePrivate)
            modifier |= Modifier.PRIVATE;
        return this;
    }

    public DeclaredMethods staticOnly() {
        modifierMask |= Modifier.STATIC;
        modifier |= Modifier.STATIC;
        return this;
    }

    public DeclaredMethods finalOnly() {
        modifierMask |= Modifier.FINAL;
        modifier |= Modifier.FINAL;
        return this;
    }

    class Iter
            extends AbstractImmediateIteratorX<Method, RuntimeException> {

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
        public Method next()
                throws RuntimeException {
            while (currentClass != null) {
                while (++currentIndex < declaredMethodsOfCurrentClass.length) {
                    Method m = declaredMethodsOfCurrentClass[currentIndex];
                    if (namePredicate != null && !namePredicate.test(m.getName()))
                        continue;
                    if (parametersPredicate != null && parametersPredicate.test(m.getParameterTypes()))
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
    public ImmediateIteratorX<? extends Method, ? extends RuntimeException> iterator(boolean allowOverlap)
            throws RuntimeException {
        return new Iter();
    }

}
