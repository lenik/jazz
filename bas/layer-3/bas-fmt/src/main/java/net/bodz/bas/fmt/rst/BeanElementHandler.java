package net.bodz.bas.fmt.rst;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.Transient;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Map;

import net.bodz.bas.c.enm.Enums;
import net.bodz.bas.c.type.TypeEnum;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.Final;

import com.thoughtworks.qdox.model.BeanProperty;

public class BeanElementHandler
        implements IElementHandler {

    private Class<?> type;
    private Map<String, PropertyDescriptor> properties;
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

        boolean isFinalField = getter.isAnnotationPresent(Final.class);

        boolean isTransientField = getter.isAnnotationPresent(Transient.class);
        if (isTransientField)
            // skipped: don't parse transient fields.
            return false; // not handled, of cause.

        Class<?> propertyType = property.getPropertyType();
        TypeEnum typeEnum = TypeEnum.forClass(propertyType);
        if (typeEnum == null)
            throw new ElementHandlerException("field type isn't supported: " + propertyType);

        Object value = null;
        int arrayLen = 0;
        if (isFinalField) {
            try {
                value = getter.invoke(obj);
            } catch (Exception e) {
                throw new ElementHandlerException("failed to read property " + attributeName, e);
            }
            if (propertyType.isArray())
                arrayLen = Array.getLength(value);
        }

        value = new RstDataParser().parse(attributeName, attributeData);

        if (!isFinalField)
            try {
                field.set(obj, value);
            } catch (Exception e) {
                throw new ElementHandlerException("failed to set the value of field " + attributeName, e);
            }
        return false;
    }

    @Override
    public IElementHandler beginChild(String name, String[] args)
            throws ParseException, ElementHandlerException {
        Field field = _getProperty(name);
        if (field == null)
            return null; // throw new ElementHandlerException("element is undefined: " + name);

        boolean isFinalField = Modifier.isFinal(field.getModifiers()) //
                || field.isAnnotationPresent(Final.class);

        Class<?> type = field.getType();
        if (!IRstSerializable.class.isAssignableFrom(type))
            throw new ElementHandlerException("field isn't structf-serializable: " + name);

        IRstSerializable value = null;
        if (isFinalField) {
            try {
                value = (IRstSerializable) field.get(obj);
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
                field.set(obj, value);
            } catch (ReflectiveOperationException e) {
                throw new ElementHandlerException("failed to write field " + name, e);
            }
        }

        return value.getElementHandler();
    }

    @Override
    public boolean endChild(IRstElement element)
            throws ElementHandlerException {
        return false;
    }

    @Override
    public void complete(IRstElement element)
            throws ElementHandlerException {
    }

}
