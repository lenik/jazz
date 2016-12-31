package net.bodz.bas.fmt.rst.obj;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.Transient;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.rst.AbstractElementHandler;
import net.bodz.bas.fmt.rst.ElementHandlerException;
import net.bodz.bas.fmt.rst.IElementHandler;
import net.bodz.bas.fmt.rst.IRstSerializable;
import net.bodz.bas.meta.decl.Final;

public class BeanElementHandler
        extends AbstractElementHandler {

    private Class<?> type;
    private Map<String, PropertyDescriptor> properties = new LinkedHashMap<>();
    private Object obj;

    public BeanElementHandler() {
        this.type = getClass();
        this.obj = this;
        init();
    }

    public BeanElementHandler(Object obj) {
        if (obj == null)
            throw new NullPointerException("obj");
        this.type = obj.getClass();
        this.obj = obj;
        init();
    }

    public BeanElementHandler(Class<?> type, Object obj) {
        if (type == null)
            throw new NullPointerException("type");
        if (obj == null)
            throw new NullPointerException("obj");
        this.type = type;
        this.obj = obj;
        init();
    }

    private void init() {
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(type);
        } catch (IntrospectionException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        for (PropertyDescriptor property : beanInfo.getPropertyDescriptors()) {
            properties.put(property.getName(), property);
        }
    }

    private PropertyDescriptor _getProperty(String name) {
        return properties.get(name);
    }

    @Override
    public boolean attribute(String attributeName, String attributeData)
            throws ParseException, ElementHandlerException {
        PropertyDescriptor property = _getProperty(attributeName);
        if (property == null)
            return false;

        Method getter = property.getReadMethod();
        Method setter = property.getWriteMethod();

        boolean isFinalField = getter.isAnnotationPresent(Final.class) || setter == null;
        boolean isTransientField = getter.isAnnotationPresent(Transient.class);
        if (isTransientField)
            // skipped: don't parse transient fields.
            return false; // not handled, of cause.

        Class<?> propertyType = property.getPropertyType();
        Object value = null;
        if (isFinalField)
            try {
                value = getter.invoke(obj);
                if (value == null)
                    return false;
            } catch (Exception e) {
                throw new ElementHandlerException("failed to read property " + attributeName, e);
            }

        Parser parser = new Parser(propertyType, value);
        value = parser.parse(attributeName, attributeData);

        if (!isFinalField)
            try {
                setter.invoke(obj, value);
            } catch (Exception e) {
                throw new ElementHandlerException("failed to set the value of field " + attributeName, e);
            }
        return false;
    }

    @Override
    public IElementHandler beginChild(String name, String[] args)
            throws ParseException, ElementHandlerException {
        PropertyDescriptor property = _getProperty(name);
        if (property == null)
            return null; // throw new ElementHandlerException("element is undefined: " + name);

        Method getter = property.getReadMethod();
        Method setter = property.getWriteMethod();

        boolean isFinalField = Modifier.isFinal(getter.getModifiers()) //
                || getter.isAnnotationPresent(Final.class);

        Class<?> type = property.getPropertyType();
        if (!IRstSerializable.class.isAssignableFrom(type))
            throw new ElementHandlerException("field isn't structf-serializable: " + name);

        IRstSerializable value = null;
        if (isFinalField) {
            try {
                value = (IRstSerializable) getter.invoke(obj);
            } catch (ReflectiveOperationException e) {
                throw new ElementHandlerException("failed to read field " + name, e);
            }
        } else {
            try {
                value = (IRstSerializable) type.newInstance(); // args ...
            } catch (ReflectiveOperationException e) {
                throw new ElementHandlerException("failed to instantiate " + type, e);
            }
            try {
                setter.invoke(obj, value);
            } catch (ReflectiveOperationException e) {
                throw new ElementHandlerException("failed to write field " + name, e);
            }
        }

        return value.getElementHandler();
    }

}
