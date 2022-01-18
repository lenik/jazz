package net.bodz.bas.fmt.xml.obj;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.api.StdValueParser;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.meta.decl.Final;

public class ReflectXmlLoader
        extends AbstractXmlLoader {

    private Class<?> type;
    private Object obj;

    public ReflectXmlLoader() {
        this.type = getClass();
        this.obj = this;
    }

    public ReflectXmlLoader(Object obj) {
        if (obj == null)
            throw new NullPointerException("obj");
        this.type = obj.getClass();
        this.obj = obj;
    }

    public ReflectXmlLoader(Class<?> type, Object obj) {
        if (type == null)
            throw new NullPointerException("type");
        if (obj == null)
            throw new NullPointerException("obj");
        this.type = type;
        this.obj = obj;
    }

    private Field _getField(String name) {
        Class<?> clazz = type;
        Field field = null;
        do {
            try {
                field = clazz.getDeclaredField(name);
                break;
            } catch (NoSuchFieldException e) {
            }
            clazz = clazz.getSuperclass();
        } while (clazz != null);

        if (field != null) {
            field.setAccessible(true);
            return field;
        } else
            return null;
    }

    @Override
    protected boolean attribute(String attributeName, String attributeData)
            throws ParseException, LoaderException {
        Field field = _getField(attributeName);
        if (field == null)
            return false;

        boolean isFinalField = Modifier.isFinal(field.getModifiers()) //
                || field.isAnnotationPresent(Final.class);

        boolean isTransientField = Modifier.isTransient(field.getModifiers()) //
        // || field.isAnnotationPresent(Transient.class)
        ;
        if (isTransientField)
            // skipped: don't parse transient fields.
            return false; // not handled, of cause.

        Class<?> fieldType = field.getType();
        Object value = null;
        if (isFinalField)
            try {
                value = field.get(obj);
                if (value == null)
                    return false;
            } catch (Exception e) {
                throw new LoaderException("failed to get field " + attributeName, e);
            }

        StdValueParser parser = new StdValueParser(fieldType, value);
        value = parser.parse(attributeName, attributeData);

        if (!isFinalField)
            try {
                field.set(obj, value);
            } catch (Exception e) {
                throw new LoaderException("failed to set the value of field " + attributeName, e);
            }
        return false;
    }

    @Override
    protected IXmlForm getChild(String name)
            throws LoaderException {
        Field field = _getField(name);
        if (field == null)
            return null; // throw new ElementHandlerException("element is undefined: " + name);

        boolean isFinalField = Modifier.isFinal(field.getModifiers()) //
                || field.isAnnotationPresent(Final.class);

        Class<?> type = field.getType();
        if (!IXmlForm.class.isAssignableFrom(type))
            throw new LoaderException("field isn't xml-serializable: " + name);

        IXmlForm value = null;
        if (isFinalField) {
            try {
                value = (IXmlForm) field.get(obj);
            } catch (ReflectiveOperationException e) {
                throw new LoaderException("failed to read field " + name, e);
            }
        } else {
            try {
                value = (IXmlForm) type.newInstance(); // args ...
            } catch (ReflectiveOperationException e) {
                throw new LoaderException("failed to instantiate " + type, e);
            }
            try {
                field.set(obj, value);
            } catch (ReflectiveOperationException e) {
                throw new LoaderException("failed to write field " + name, e);
            }
        }
        return value;
    }

}
