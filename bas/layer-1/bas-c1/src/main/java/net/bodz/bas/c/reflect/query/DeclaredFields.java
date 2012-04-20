package net.bodz.bas.c.reflect.query;

import java.lang.reflect.Field;

import net.bodz.bas.util.iter.AbstractMitorx;
import net.bodz.bas.util.iter.Mitorx;

/**
 * @test {@link DeclaredFieldsTest}
 */
public class DeclaredFields
        extends FieldSelection {

    private final Class<?> clazz;

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
        int currentIndex;

        public Iter() {
            if (stopClass == null || !clazz.isAssignableFrom(stopClass)) {
                currentClass = clazz;
                declaredFieldsOfCurrentClass = currentClass.getDeclaredFields();
                currentIndex = -1;
            }
        }

        @Override
        public Field _next()
                throws RuntimeException {
            while (currentClass != null) {
                while (++currentIndex < declaredFieldsOfCurrentClass.length) {
                    Field field = declaredFieldsOfCurrentClass[currentIndex];
                    if (modifierTest != (modifierMask & field.getModifiers()))
                        continue;
                    if (namePredicate != null && !namePredicate.evaluate(field.getName()))
                        continue;
                    if (typePredicate != null && !typePredicate.evaluate(field.getType()))
                        continue;
                    return field;
                }
                currentClass = currentClass.getSuperclass();
                if (currentClass == null)
                    break;
                if (stopClass != null && currentClass.isAssignableFrom(stopClass))
                    break;
                declaredFieldsOfCurrentClass = currentClass.getDeclaredFields();
                currentIndex = -1;
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
