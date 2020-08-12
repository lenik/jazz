package net.bodz.bas.fmt.rst.obj;

import com.googlecode.openbeans.BeanInfo;
import com.googlecode.openbeans.IntrospectionException;
import com.googlecode.openbeans.Introspector;
import com.googlecode.openbeans.PropertyDescriptor;
import com.googlecode.openbeans.Transient;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

import net.bodz.bas.fmt.rst.AbstractRstDumper;
import net.bodz.bas.fmt.rst.IRstFormat;
import net.bodz.bas.fmt.rst.IRstOutput;

public class BeanRstDumper
        extends AbstractRstDumper {

    public BeanRstDumper(IRstOutput out) {
        super(out);
    }

    @Override
    protected void formatObject(Class<?> clazz, Object obj)
            throws IOException {
        marks.add(obj);

        IRstFormat formatOverride = null;
        if (obj instanceof IRstFormat)
            formatOverride = (IRstFormat) obj;

        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(clazz);
        } catch (IntrospectionException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        marks.enter();
        for (PropertyDescriptor property : beanInfo.getPropertyDescriptors()) {
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

            if (getter.isAnnotationPresent(Transient.class))
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
            formatCollectionMember(name, type, gtype, value);
        } // for field
        marks.leave();
    }

}
