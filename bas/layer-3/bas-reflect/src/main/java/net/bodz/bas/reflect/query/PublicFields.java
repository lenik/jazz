package net.bodz.bas.reflect.query;

import java.lang.reflect.Field;

import net.bodz.bas.collection.iterator.AbstractImmediateIteratorX;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;

public class PublicFields
        extends FieldSelection {

    private final Class<?> clazz;

    public PublicFields(Class<?> clazz) {
        if (clazz == null)
            throw new NullPointerException("clazz");
        this.clazz = clazz;
    }

    class Iter
            extends AbstractImmediateIteratorX<Field, RuntimeException> {

        Field[] methods = clazz.getFields();
        int currentIndex = -1;

        @Override
        public Field next()
                throws RuntimeException {
            while (++currentIndex < methods.length) {
                Field field = methods[currentIndex];
                if (modifierTest != (modifierMask & field.getModifiers()))
                    continue;
                if (namePredicate != null && !namePredicate.test(field.getName()))
                    continue;
                if (typePredicate != null && !typePredicate.test(field.getType()))
                    continue;
                return field;
            }
            return end();
        }
    }

    @Override
    public ImmediateIteratorX<? extends Field, ? extends RuntimeException> iterator(boolean allowOverlap)
            throws RuntimeException {
        return new Iter();
    }

}
