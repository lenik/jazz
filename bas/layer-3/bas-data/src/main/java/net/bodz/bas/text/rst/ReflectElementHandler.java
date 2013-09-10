package net.bodz.bas.text.rst;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import net.bodz.bas.c.type.TypeEnum;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.ProcessException;

public class ReflectElementHandler
        implements IElementHandler {

    private Class<?> type;
    private Object obj;

    public ReflectElementHandler(Object obj) {
        if (obj == null)
            throw new NullPointerException("obj");
        this.type = obj.getClass();
        this.obj = obj;
    }

    public ReflectElementHandler(Class<?> type, Object obj) {
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
    public boolean attribute(String name, String data)
            throws ProcessException, ParseException {
        Field field = _getField(name);
        if (field == null)
            throw new ProcessException("attribute is undefined: " + name);

        boolean _final = Modifier.isFinal(field.getModifiers());
        Class<?> type = field.getType();
        TypeEnum typeEnum = TypeEnum.fromClass(type);
        if (typeEnum == null)
            throw new ProcessException("property type isn't supported: " + type);

        Object value = null;
        int arrayLen = 0;
        if (_final) {
            if (type.isArray())
                arrayLen = Array.getLength(value);
            try {
                value = field.get(obj);
            } catch (Exception e) {
                throw new ProcessException("failed to read property " + name, e);
            }
        }

        switch (typeEnum) {
        case BYTE:
            value = codec.parseByte(name, data);
            break;

        case SHORT:
            value = codec.parseShort(name, data);
            break;

        case INT:
            value = codec.parseInt(name, data);
            break;

        case LONG:
            value = codec.parseLong(name, data);
            break;

        case FLOAT:
            value = codec.parseFloat(name, data);
            break;

        case DOUBLE:
            value = codec.parseDouble(name, data);
            break;

        case BOOL:
            value = codec.parseBool(name, data);
            break;

        case CHAR:
            value = codec.parseChar(name, data);
            break;

        case STRING:
            value = codec.parseString(name, data);
            break;

        case CLASS:
            String typeName = codec.parseString(name, data);
            try {
                value = Class.forName(typeName);
            } catch (ClassNotFoundException e) {
                throw new ParseException("undefined class: " + typeName, e);
            }
            break;

        case BYTE_ARRAY:
            if (_final) {
                byte[] v = (byte[]) value;
                int n = codec.parseBytes(name, data, v, 0, arrayLen);
                Arrays.fill(v, n, arrayLen, (byte) 0);
            } else {
                value = codec.parseBytes(name, data);
            }
            break;

        case SHORT_ARRAY:
            if (_final) {
                short[] v = (short[]) value;
                int n = codec.parseShorts(name, data, v, 0, arrayLen);
                Arrays.fill(v, n, arrayLen, (short) 0);
            } else {
                value = codec.parseShorts(name, data);
            }
            break;

        case INT_ARRAY:
            if (_final) {
                int[] v = (int[]) value;
                int n = codec.parseInts(name, data, v, 0, arrayLen);
                Arrays.fill(v, n, arrayLen, (int) 0);
            } else {
                value = codec.parseInts(name, data);
            }
            break;

        case LONG_ARRAY:
            if (_final) {
                long[] v = (long[]) value;
                int n = codec.parseLongs(name, data, v, 0, arrayLen);
                Arrays.fill(v, n, arrayLen, 0L);
            } else {
                value = codec.parseLongs(name, data);
            }
            break;

        case FLOAT_ARRAY:
            if (_final) {
                float[] v = (float[]) value;
                int n = codec.parseFloats(name, data, v, 0, arrayLen);
                Arrays.fill(v, n, arrayLen, 0.0f);
            } else {
                value = codec.parseFloats(name, data);
            }
            break;

        case DOUBLE_ARRAY:
            if (_final) {
                double[] v = (double[]) value;
                int n = codec.parseDoubles(name, data, v, 0, arrayLen);
                Arrays.fill(v, n, arrayLen, Double.NaN);
            } else {
                value = codec.parseDoubles(name, data);
            }
            break;

        case BOOL_ARRAY:
            if (_final) {
                boolean[] v = (boolean[]) value;
                int n = codec.parseBools(name, data, v, 0, arrayLen);
                Arrays.fill(v, n, arrayLen, false);
            } else {
                value = codec.parseBools(name, data);
            }
            break;

        case CHAR_ARRAY:
            if (_final) {
                char[] v = (char[]) value;
                int n = codec.parseChars(name, data, v, 0, arrayLen);
                Arrays.fill(v, n, arrayLen, '\0');
            } else {
                value = codec.parseChars(name, data);
            }
            break;

        case STRING_ARRAY:
            if (_final) {
                String[] v = (String[]) value;
                int n = codec.parseStrings(name, data, v, 0, arrayLen);
                Arrays.fill(v, n, arrayLen, null);
            } else {
                value = codec.parseStrings(name, data);
            }
            break;

        case CLASS_ARRAY:
            String[] typeNames = codec.parseStrings(name, data);
            if (_final) {
                Class<?>[] v = (Class<?>[]) value;
                int n = Math.min(typeNames.length, v.length);
                for (int i = 0; i < n; i++) {
                    try {
                        v[i] = Class.forName(typeNames[i]);
                    } catch (ClassNotFoundException e) {
                        throw new ParseException("undefined class: " + typeNames[i], e);
                    }
                }
            } else {
                Class<?>[] types = new Class<?>[typeNames.length];
                for (int i = 0; i < types.length; i++)
                    try {
                        types[i] = Class.forName(typeNames[i]);
                    } catch (ClassNotFoundException e) {
                        throw new ParseException("undefined class: " + typeNames[i], e);
                    }
                value = types;
            }
            break;

        case OBJECT:
        default:
            throw new ProcessException("property type isn't supported: " + type);
        }

        if (!_final)
            try {
                field.set(obj, value);
            } catch (Exception e) {
                throw new ProcessException("failed to set the value of property " + name, e);
            }
        return false;
    }

    @Override
    public IElementHandler beginChild(String name, String[] args)
            throws ProcessException {
        Field field = _getField(name);
        if (field == null)
            throw new ProcessException("element is undefined: " + name);

        boolean _final = Modifier.isFinal(field.getModifiers());
        Class<?> type = field.getType();
        if (!IRstSerializable.class.isAssignableFrom(type))
            throw new ProcessException("property isn't structf-serializable: " + name);

        IRstSerializable value = null;
        if (_final) {
            try {
                value = (IRstSerializable) field.get(obj);
            } catch (ReflectiveOperationException e) {
                throw new ProcessException("failed to read property " + name, e);
            }
        } else {
            try {
                value = (IRstSerializable) type.newInstance(); // args ...
            } catch (ReflectiveOperationException e) {
                throw new ProcessException("failed to instantiate " + type, e);
            }
            try {
                field.set(obj, value);
            } catch (ReflectiveOperationException e) {
                throw new ProcessException("failed to write property " + name, e);
            }
        }

        return value.getElementHandler();
    }

    @Override
    public boolean endChild(IRstElement element)
            throws ProcessException {
        return true;
    }

    @Override
    public void complete(IRstElement element)
            throws ProcessException {
    }

}
