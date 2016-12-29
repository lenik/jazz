package net.bodz.bas.fmt.rst;

import java.beans.Transient;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import net.bodz.bas.c.enm.Enums;
import net.bodz.bas.c.type.TypeEnum;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.Final;

public class ReflectElementHandler
        implements IElementHandler {

    private Class<?> type;
    private Object obj;

    public ReflectElementHandler() {
        this.type = getClass();
        this.obj = this;
    }

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
    public boolean attribute(String attributeName, String attributeData)
            throws ParseException, ElementHandlerException {
        Field field = _getField(attributeName);
        if (field == null)
            return false;

        boolean isFinalField = Modifier.isFinal(field.getModifiers()) //
                || field.isAnnotationPresent(Final.class);
        ;
        boolean isTransientField = Modifier.isTransient(field.getModifiers()) //
        // || field.isAnnotationPresent(Transient.class)
        ;
        if (isTransientField)
            // skipped: don't parse transient fields.
            return false; // not handled, of cause.

        Class<?> fieldType = field.getType();
        TypeEnum typeEnum = TypeEnum.forClass(fieldType);
        if (typeEnum == null)
            throw new ElementHandlerException("field type isn't supported: " + fieldType);

        Object value = null;
        int arrayLen = 0;
        if (isFinalField) {
            try {
                value = field.get(obj);
            } catch (Exception e) {
                throw new ElementHandlerException("failed to get field " + attributeName, e);
            }
            if (fieldType.isArray())
                arrayLen = Array.getLength(value);
        }

        switch (typeEnum) {
        case BYTE:
            value = codec.parseByte(attributeName, attributeData);
            break;

        case SHORT:
            value = codec.parseShort(attributeName, attributeData);
            break;

        case INT:
            value = codec.parseInt(attributeName, attributeData);
            break;

        case LONG:
            value = codec.parseLong(attributeName, attributeData);
            break;

        case FLOAT:
            value = codec.parseFloat(attributeName, attributeData);
            break;

        case DOUBLE:
            value = codec.parseDouble(attributeName, attributeData);
            break;

        case BOOL:
            value = codec.parseBool(attributeName, attributeData);
            break;

        case CHAR:
            value = codec.parseChar(attributeName, attributeData);
            break;

        case STRING:
            value = codec.parseString(attributeName, attributeData);
            break;

        case CLASS:
            String typeName = codec.parseString(attributeName, attributeData);
            try {
                value = Class.forName(typeName);
            } catch (ClassNotFoundException e) {
                throw new ParseException("undefined class: " + typeName, e);
            }
            break;

        case ENUM:
            value = Enums.getEnum(fieldType, attributeData);
            break;

        case BYTE_ARRAY:
            if (isFinalField) {
                byte[] v = (byte[]) value;
                int n = codec.parseBytes(attributeName, attributeData, v, 0, arrayLen);
                Arrays.fill(v, n, arrayLen, (byte) 0);
            } else {
                value = codec.parseBytes(attributeName, attributeData);
            }
            break;

        case SHORT_ARRAY:
            if (isFinalField) {
                short[] v = (short[]) value;
                int n = codec.parseShorts(attributeName, attributeData, v, 0, arrayLen);
                Arrays.fill(v, n, arrayLen, (short) 0);
            } else {
                value = codec.parseShorts(attributeName, attributeData);
            }
            break;

        case INT_ARRAY:
            if (isFinalField) {
                int[] v = (int[]) value;
                int n = codec.parseInts(attributeName, attributeData, v, 0, arrayLen);
                Arrays.fill(v, n, arrayLen, (int) 0);
            } else {
                value = codec.parseInts(attributeName, attributeData);
            }
            break;

        case LONG_ARRAY:
            if (isFinalField) {
                long[] v = (long[]) value;
                int n = codec.parseLongs(attributeName, attributeData, v, 0, arrayLen);
                Arrays.fill(v, n, arrayLen, 0L);
            } else {
                value = codec.parseLongs(attributeName, attributeData);
            }
            break;

        case FLOAT_ARRAY:
            if (isFinalField) {
                float[] v = (float[]) value;
                int n = codec.parseFloats(attributeName, attributeData, v, 0, arrayLen);
                Arrays.fill(v, n, arrayLen, 0.0f);
            } else {
                value = codec.parseFloats(attributeName, attributeData);
            }
            break;

        case DOUBLE_ARRAY:
            if (isFinalField) {
                double[] v = (double[]) value;
                int n = codec.parseDoubles(attributeName, attributeData, v, 0, arrayLen);
                Arrays.fill(v, n, arrayLen, Double.NaN);
            } else {
                value = codec.parseDoubles(attributeName, attributeData);
            }
            break;

        case BOOL_ARRAY:
            if (isFinalField) {
                boolean[] v = (boolean[]) value;
                int n = codec.parseBools(attributeName, attributeData, v, 0, arrayLen);
                Arrays.fill(v, n, arrayLen, false);
            } else {
                value = codec.parseBools(attributeName, attributeData);
            }
            break;

        case CHAR_ARRAY:
            if (isFinalField) {
                char[] v = (char[]) value;
                int n = codec.parseChars(attributeName, attributeData, v, 0, arrayLen);
                Arrays.fill(v, n, arrayLen, '\0');
            } else {
                value = codec.parseChars(attributeName, attributeData);
            }
            break;

        case STRING_ARRAY:
            if (isFinalField) {
                String[] v = (String[]) value;
                int n = codec.parseStrings(attributeName, attributeData, v, 0, arrayLen);
                Arrays.fill(v, n, arrayLen, null);
            } else {
                value = codec.parseStrings(attributeName, attributeData);
            }
            break;

        case CLASS_ARRAY:
            String[] typeNames = codec.parseStrings(attributeName, attributeData);
            if (isFinalField) {
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

//        case ENUM_ARRAY:
//            if (isFinalField) {
//                Enum<?>[] v = (Enum<?>[]) value;
//                int n = codec.parseEnums(fieldType, attributeName, attributeData);
//                Arrays.fill(v, n, arrayLen, '\0');
//            } else {
//                value = codec.parseEnums(attributeName, attributeData);
//            }
//            break;

        case OBJECT:
            throw new ElementHandlerException("Don't know how to parse: " + fieldType);

        default:
            throw new ElementHandlerException("field type isn't supported: " + fieldType);
        }

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
        Field field = _getField(name);
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
