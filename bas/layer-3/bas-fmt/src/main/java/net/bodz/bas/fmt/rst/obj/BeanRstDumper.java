package net.bodz.bas.fmt.rst.obj;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

import net.bodz.bas.bean.api.IBeanInfo;
import net.bodz.bas.bean.api.IPropertyDescriptor;
import net.bodz.bas.bean.api.IntrospectionException;
import net.bodz.bas.bean.api.Introspectors;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.rst.AbstractRstDumper;
import net.bodz.bas.fmt.rst.IRstOutput;
import net.bodz.bas.fmt.rst.IRstOverrides;
import net.bodz.bas.meta.bean.Transient;

public class BeanRstDumper
        extends AbstractRstDumper {

    public BeanRstDumper(IRstOutput out) {
        super(out);
    }

    @Override
    protected void formatObject(Class<?> clazz, Object obj)
            throws IOException, FormatException {
        marks.addMark(obj);

        IRstOverrides formatOverride = null;
        if (obj instanceof IRstOverrides)
            formatOverride = (IRstOverrides) obj;

        IBeanInfo beanInfo;
        try {
            beanInfo = Introspectors.getBeanInfo(clazz);
        } catch (IntrospectionException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        marks.enter();
        for (IPropertyDescriptor property : beanInfo.getPropertyDescriptors()) {
            String name = property.getName();
            Class<?> type = property.getPropertyType();
            Method getter = property.getReadMethod();
            Method setter = property.getWriteMethod();

            if (getter == null)
                throw new NullPointerException("getter");

            if (setter == null && type.isPrimitive()) // read-only
                continue; // TODO more

            Class<?> declaringClass = getter.getDeclaringClass();
            if (stopClasses.contains(declaringClass))
                continue;

            int modifiers = getter.getModifiers();
            if (Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers))
                continue;

            // if (getter.isAnnotationPresent(java.beans.Transient.class)) continue;
            if (getter.isAnnotationPresent(Transient.class))
                continue;

            if (formatOverride != null)
                if (formatOverride.writeSpecialRstEntry(out, name))
                    continue;

            Object value;
            try {
                value = getter.invoke(obj);
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

            Type gtype = getter.getGenericReturnType();
            formatCollectionMember(name, type, gtype, value);
        } // for field
        marks.leave();
    }

}
