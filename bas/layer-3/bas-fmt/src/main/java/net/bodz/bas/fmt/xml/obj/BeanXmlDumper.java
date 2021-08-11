package net.bodz.bas.fmt.xml.obj;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.xml.AbstractXmlDumper;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.IXmlOverrides;
import net.bodz.bas.meta.bean.Transient;

import com.googlecode.openbeans.BeanInfo;
import com.googlecode.openbeans.IntrospectionException;
import com.googlecode.openbeans.Introspector;
import com.googlecode.openbeans.PropertyDescriptor;

public class BeanXmlDumper
        extends AbstractXmlDumper {

    public BeanXmlDumper(IXmlOutput out) {
        super(out);
    }

    @Override
    protected void formatObject(Class<?> clazz, Object obj)
            throws XMLStreamException, FormatException {
        marks.add(obj);

        IXmlOverrides formatOverride = null;
        if (obj instanceof IXmlOverrides)
            formatOverride = (IXmlOverrides) obj;

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
                if (formatOverride.writeSpecialXmlEntry(out, name))
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
