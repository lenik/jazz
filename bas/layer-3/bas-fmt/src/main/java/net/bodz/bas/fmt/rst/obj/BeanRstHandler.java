package net.bodz.bas.fmt.rst.obj;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.bean.api.IBeanInfo;
import net.bodz.bas.bean.api.IPropertyDescriptor;
import net.bodz.bas.bean.api.IntrospectionException;
import net.bodz.bas.bean.api.Introspectors;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.api.ElementHandlerException;
import net.bodz.bas.fmt.api.StdValueParser;
import net.bodz.bas.fmt.rst.AbstractRstHandler;
import net.bodz.bas.fmt.rst.IRstForm;
import net.bodz.bas.fmt.rst.IRstHandler;
import net.bodz.bas.meta.bean.Transient;
import net.bodz.bas.meta.decl.Final;

public class BeanRstHandler
        extends AbstractRstHandler {

    private Class<?> type;
    private Map<String, IPropertyDescriptor> properties = new LinkedHashMap<>();
    private Object obj;

    public BeanRstHandler() {
        this.type = getClass();
        this.obj = this;
        init();
    }

    public BeanRstHandler(Object obj) {
        if (obj == null)
            throw new NullPointerException("obj");
        this.type = obj.getClass();
        this.obj = obj;
        init();
    }

    public BeanRstHandler(Class<?> type, Object obj) {
        if (type == null)
            throw new NullPointerException("type");
        if (obj == null)
            throw new NullPointerException("obj");
        this.type = type;
        this.obj = obj;
        init();
    }

    private void init() {
        IBeanInfo beanInfo;
        try {
            beanInfo = Introspectors.getBeanInfo(type);
        } catch (IntrospectionException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        for (IPropertyDescriptor property : beanInfo.getPropertyDescriptors()) {
            properties.put(property.getName(), property);
        }
    }

    private IPropertyDescriptor _getProperty(String name) {
        return properties.get(name);
    }

    @Override
    public boolean attribute(String attributeName, String attributeData)
            throws ParseException, ElementHandlerException {
        IPropertyDescriptor property = _getProperty(attributeName);
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

        StdValueParser parser = new StdValueParser(propertyType, value);
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
    public IRstHandler beginChild(String name, String[] args)
            throws ParseException, ElementHandlerException {
        IPropertyDescriptor property = _getProperty(name);
        if (property == null)
            return null; // throw new ElementHandlerException("element is undefined: " + name);

        Method getter = property.getReadMethod();
        Method setter = property.getWriteMethod();

        boolean isFinalField = Modifier.isFinal(getter.getModifiers()) //
                || getter.isAnnotationPresent(Final.class);

        Class<?> type = property.getPropertyType();
        if (!IRstForm.class.isAssignableFrom(type))
            throw new ElementHandlerException("field isn't structf-serializable: " + name);

        IRstForm value = null;
        if (isFinalField) {
            try {
                value = (IRstForm) getter.invoke(obj);
            } catch (ReflectiveOperationException e) {
                throw new ElementHandlerException("failed to read field " + name, e);
            }
        } else {
            try {
                value = (IRstForm) type.newInstance(); // args ...
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
