package net.bodz.bas.fmt.xml.obj;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.fmt.xml.AbstractXmlDumper;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.IXmlOverrides;

public class ReflectXmlDumper extends AbstractXmlDumper {

    public ReflectXmlDumper(IXmlOutput out) {
        super(out);
    }

    @Override
    protected void formatObject(Class<?> clazz, Object obj)
            throws XMLStreamException {
        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null)
            if (!stopClasses.contains(superclass))
                formatObject(superclass, obj);

        IXmlOverrides formatOverride = null;
        if (obj instanceof IXmlOverrides)
            formatOverride = (IXmlOverrides) obj;

        for (Field field : clazz.getDeclaredFields()) {
            String name = field.getName();
            int modifiers = field.getModifiers();
            if (Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers))
                continue;

            if (formatOverride != null)
                if (formatOverride.writeSpecialXmlEntry(out, name))
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
