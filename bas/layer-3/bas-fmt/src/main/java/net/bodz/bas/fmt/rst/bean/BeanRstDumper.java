package net.bodz.bas.fmt.rst.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

import net.bodz.bas.fmt.rst.AbstractRstDumper;
import net.bodz.bas.fmt.rst.IRstFormat;
import net.bodz.bas.fmt.rst.IRstOutput;

public class BeanRstDumper
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

        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(clazz);
        } catch (IntrospectionException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        for (PropertyDescriptor property : beanInfo.getPropertyDescriptors()) {
            String name = property.getName();
            Class<?> type = property.getPropertyType();
            Method getter = property.getReadMethod();
            Method setter = property.getWriteMethod();

            if (setter == null && type.isPrimitive())
                continue; // TODO more

            int modifiers = getter.getModifiers();
            if (Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers))
                continue;

            if (formatOverride != null)
                if (formatOverride.writeEntryOverride(out, name))
                    continue;

            Object value;
            try {
                value = getter.invoke(obj);
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

            Type gtype = getter.getGenericReturnType();
            encode(out, name, type, gtype, value);
        } // for field
    }

    private static BeanRstDumper instance = new BeanRstDumper();

    public static BeanRstDumper getInstance() {
        return instance;
    }

}
