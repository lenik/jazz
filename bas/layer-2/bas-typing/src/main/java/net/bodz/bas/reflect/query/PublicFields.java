package net.bodz.bas.reflect.query;

import java.lang.reflect.Field;

import net.bodz.bas.util.iter.AbstractMitorx;
import net.bodz.bas.util.iter.Mitorx;

public class PublicFields
        extends FieldSelection {

    private final Class<?> clazz;

    public PublicFields(Class<?> clazz) {
        if (clazz == null)
            throw new NullPointerException("clazz");
        this.clazz = clazz;
    }

    class Iter
            extends AbstractMitorx<Field, RuntimeException> {

        Field[] methods = clazz.getFields();
        int currentIndex = -1;

        @Override
        public Field _next()
                throws RuntimeException {
            while (++currentIndex < methods.length) {
                Field field = methods[currentIndex];
                if (modifierTest != (modifierMask & field.getModifiers()))
                    continue;
                if (namePredicate != null && !namePredicate.evaluate(field.getName()))
                    continue;
                if (typePredicate != null && !typePredicate.evaluate(field.getType()))
                    continue;
                return field;
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
