package net.bodz.bas.fmt.rst.obj;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.rst.AbstractRstDumper;
import net.bodz.bas.fmt.rst.IRstOutput;
import net.bodz.bas.fmt.rst.IRstOverrides;

public class ReflectRstDumper
        extends AbstractRstDumper {

    public ReflectRstDumper(IRstOutput out) {
        super(out);
    }

    @Override
    protected void formatObject(Class<?> clazz, Object obj)
            throws IOException, FormatException {
        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null)
            if (!stopClasses.contains(superclass))
                formatObject(superclass, obj);

        IRstOverrides formatOverride = null;
        if (obj instanceof IRstOverrides)
            formatOverride = (IRstOverrides) obj;

        for (Field field : clazz.getDeclaredFields()) {
            String name = field.getName();
            int modifiers = field.getModifiers();
            if (Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers))
                continue;

            if (formatOverride != null)
                if (formatOverride.writeSpecialRstEntry(out, name))
                    continue;

            field.setAccessible(true);

            Object value;
            try {
                value = field.get(obj);
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

            formatCollectionMember(name, field.getType(), field.getGenericType(), value);
        } // for field
    }

}
