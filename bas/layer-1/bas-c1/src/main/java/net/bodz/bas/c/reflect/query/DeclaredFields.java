package net.bodz.bas.c.reflect.query;

import java.lang.reflect.Field;

import net.bodz.bas.t.iterator.immed.AbstractMitorx;
import net.bodz.bas.t.iterator.immed.Mitorx;

/**
 * @test {@link DeclaredFieldsTest}
 */
public class DeclaredFields
        extends FieldSelection {

    private final Class<?> clazz;

    protected int maxDepth = Integer.MAX_VALUE;
    protected Class<?> stopClass;

    /**
     * @throws NullPointerException
     *             If <code>clazz</code> is <code>null</code>.
     */
    public DeclaredFields(Class<?> clazz) {
        if (clazz == null)
            throw new NullPointerException("clazz");
        this.clazz = clazz;
    }

    public DeclaredFields maxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
        return this;
    }

    /**
     * @param stopClass
     *            <code>null</code> If not constrained.
     */
    public DeclaredFields stopAt(Class<?> stopClass) {
        this.stopClass = stopClass;
        return this;
    }

    class Iter
            extends AbstractMitorx<Field, RuntimeException> {

        Class<?> currentClass;
        Field[] declaredFieldsOfCurrentClass;
        int nextIndex;
        int currentDepth; // [ 0 .. maxDepth)

        public Iter() {
            if (stopClass == null || !clazz.isAssignableFrom(stopClass)) {
                currentClass = clazz;
                declaredFieldsOfCurrentClass = currentClass.getDeclaredFields();
                nextIndex = 0;
            }
        }

        @Override
        public Field _next()
                throws RuntimeException {
            while (currentClass != null && currentDepth < maxDepth) {

                while (nextIndex < declaredFieldsOfCurrentClass.length) {
                    Field field = declaredFieldsOfCurrentClass[nextIndex++];
                    if (modifierTest != (modifierMask & field.getModifiers()))
                        continue;
                    if (namePredicate != null && !namePredicate.evaluate(field.getName()))
                        continue;
                    if (typePredicate != null && !typePredicate.evaluate(field.getType()))
                        continue;
                    return field;
                }

                if (currentDepth < maxDepth) {
                    currentClass = currentClass.getSuperclass();
                    currentDepth++;
                    if (currentClass == null)
                        break;
                } else {
                    break;
                }

                if (stopClass != null && currentClass.isAssignableFrom(stopClass))
                    break;
                declaredFieldsOfCurrentClass = currentClass.getDeclaredFields();
                nextIndex = 0;
            }
            return end();
        }
    }

    @Override
    public Mitorx<? extends Field, ? extends RuntimeException> iterator(boolean allowOverlap)
            throws RuntimeException {
        return new Iter();
    }

}
