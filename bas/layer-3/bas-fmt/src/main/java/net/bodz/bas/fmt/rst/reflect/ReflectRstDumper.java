package net.bodz.bas.fmt.rst.reflect;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.bodz.bas.fmt.rst.AbstractRstDumper;
import net.bodz.bas.fmt.rst.IRstFormat;
import net.bodz.bas.fmt.rst.IRstOutput;

public class ReflectRstDumper
        extends AbstractRstDumper {

    @Override
    protected void _dump(IRstOutput out, Object obj, Class<?> clazz)
            throws IOException {
        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null)
            if (!stopClasses.contains(superclass))
                _dump(out, obj, superclass);

        IRstFormat formatOverride = null;
        if (obj instanceof IRstFormat)
            formatOverride = (IRstFormat) obj;

        for (Field field : clazz.getDeclaredFields()) {
            String name = field.getName();
            int modifiers = field.getModifiers();
            if (Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers))
                continue;

            if (formatOverride != null)
                if (formatOverride.writeEntryOverride(out, name))
                    continue;

            field.setAccessible(true);

            Object value;
            try {
                value = field.get(obj);
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

            encode(out, name, field.getType(), field.getGenericType(), value);
        } // for field
    }

    private static ReflectRstDumper instance = new ReflectRstDumper();

    public static ReflectRstDumper getInstance() {
        return instance;
    }

}
