package net.bodz.swt.auto;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public abstract class AbstractVisualType
        implements VisualType {

    public abstract Class<?>[] supportTypes();

    public abstract void render(Object data, Control target);

    public abstract Object capture(Control source, Object data);

    public abstract Control createControl(Composite parent);

    public void removeControl(Control control) {
    }

    public void render(Object data, Field field, Control target) {
        assert data != null;
        try {
            Object o = field.get(data);
            render(o, target);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void render(Object data, String fieldName, Control target) {
        assert data != null;
        Class<?> clazz = data.getClass();

        Field field;
        try {
            field = clazz.getField(fieldName);
            render(data, field, target);
            return;
        } catch (NoSuchFieldException e) {
            // Try Get Method
        }

        Method method = getGetMethod(clazz, fieldName);
        if (method == null)
            throw new IllegalArgumentException("Neither fieldName nor getFieldName exists: " + clazz.getName() + "::"
                    + fieldName);

        Object getResult;
        try {
            getResult = method.invoke(data, new Object[0]);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        render(getResult, target);
    }

    public void capture(Control source, Object data, Field field) {
        assert data != null;
        try {
            Object o = field.get(data);
            // XXX
            Object captured = capture(source, o);
            if (o != captured)
                field.set(data, captured);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void capture(Control source, Object data, String fieldName) {
        assert data != null;
        Class<?> clazz = data.getClass();

        Field field;
        try {
            field = clazz.getField(fieldName);
            capture(source, data, field);
            return;
        } catch (NoSuchFieldException e) {
            // Try get/set method
        }

        Class<?> type = getFieldType(clazz, fieldName);

        Method method = getGetMethod(clazz, fieldName);
        assert method != null;

        Object o;
        try {
            o = method.invoke(data, new Object[0]);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        o = capture(source, o);

        method = getSetMethod(clazz, fieldName, type);
        if (method == null)
            throw new IllegalArgumentException("Neither fieldName nor setFieldName exists: " + clazz.getName() + "::"
                    + fieldName);

        try {
            method.invoke(data, new Object[] { o });
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    protected static String capFirst(String string) {
        assert string.length() > 0;
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }

    public static Method getGetMethod(Class<?> clazz, String fieldName) {
        try {
            return clazz.getMethod("get" + capFirst(fieldName), new Class[0]);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static Method getSetMethod(Class<?> clazz, String fieldName, Class<?> retType) {
        try {
            return clazz.getMethod("set" + capFirst(fieldName), new Class[] { retType });
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static Class<?> getFieldType(Class<?> clazz, String fieldName) {
        Method method = getGetMethod(clazz, fieldName);
        if (method == null)
            throw new IllegalArgumentException("Neither fieldName nor getFieldName exists: " + clazz.getName() + "::"
                    + fieldName);

        Class<?> type = method.getReturnType();
        return type;
    }
}
