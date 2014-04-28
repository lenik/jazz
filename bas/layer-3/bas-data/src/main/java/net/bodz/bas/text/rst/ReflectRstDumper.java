package net.bodz.bas.text.rst;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.c.type.TypeArray;
import net.bodz.bas.c.type.TypeEnum;
import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.l10n.en.English;

public class ReflectRstDumper {

    private Set<Class<?>> stopClasses = new HashSet<>();
    {
        stopClasses.add(Object.class);
        stopClasses.add(ReflectElementHandler.class);
    }

    public String dump(Object obj) {
        BCharOut buf = new BCharOut();
        IRstOutput out = RstOutputImpl.from(buf);
        try {
            dump(out, obj);
        } catch (IOException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
        return buf.toString();
    }

    public void dump(IRstOutput out, Object obj)
            throws IOException {
        _dump(out, obj, obj.getClass());
    }

    void _dump(IRstOutput out, Object obj, Class<?> clazz)
            throws IOException {
        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null)
            if (!stopClasses.contains(superclass))
                _dump(out, obj, superclass);

        IReflectRstOverrides overrides = null;
        if (obj instanceof IReflectRstOverrides)
            overrides = (IReflectRstOverrides) obj;

        for (Field field : clazz.getDeclaredFields()) {
            int modifiers = field.getModifiers();
            if (Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers))
                continue;

            if (overrides != null)
                if (overrides.writeObjectFieldOverride(out, field))
                    continue;

            field.setAccessible(true);

            String name = field.getName();
            Object _value;
            try {
                _value = field.get(obj);
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

            if (_value == null)
                continue;

            Class<?> type = field.getType();

            Collection<?> collection;
            if (_value instanceof Collection<?>) {
                collection = (Collection<?>) _value;
                name = English.singularOf(name);
                type = TypeParam.infer1(field.getGenericType(), Collection.class, 0);
            } else {
                collection = Arrays.asList(_value);
            }

            for (Object value : collection) {
                if (value instanceof IRstSerializable) {
                    IRstSerializable child = (IRstSerializable) value;
                    String args[] = {};
                    if (child instanceof IReflectRstOverrides)
                        args = ((IReflectRstOverrides) child).getElementArguments();

                    out.beginElement(name, args);
                    child.writeObject(out);
                    out.endElement();
                    continue;
                }

                TypeEnum typeEnum = TypeEnum.fromClass(type);
                if (typeEnum == null) // skip unknown type.
                    continue;

                switch (typeEnum) {
                case BYTE:
                    out.attribute(name, (byte) value);
                    break;

                case SHORT:
                    out.attribute(name, (short) value);
                    break;

                case INT:
                    out.attribute(name, (int) value);
                    break;

                case LONG:
                    out.attribute(name, (long) value);
                    break;

                case FLOAT:
                    out.attribute(name, (float) value);
                    break;

                case DOUBLE:
                    out.attribute(name, (double) value);
                    break;

                case BOOL:
                    out.attribute(name, (boolean) value);
                    break;

                case CHAR:
                    out.attribute(name, (char) value);
                    break;

                case STRING:
                    out.attribute(name, (String) value);
                    break;

                case CLASS:
                    out.attribute(name, ((Class<?>) value).getCanonicalName());
                    break;

                case BYTE_ARRAY:
                    out.attribute(name, (byte[]) value);
                    break;

                case SHORT_ARRAY:
                    out.attribute(name, (short[]) value);
                    break;

                case INT_ARRAY:
                    out.attribute(name, (int[]) value);
                    break;

                case LONG_ARRAY:
                    out.attribute(name, (long[]) value);
                    break;

                case FLOAT_ARRAY:
                    out.attribute(name, (float[]) value);
                    break;

                case DOUBLE_ARRAY:
                    out.attribute(name, (double[]) value);
                    break;

                case BOOL_ARRAY:
                    out.attribute(name, (boolean[]) value);
                    break;

                case CHAR_ARRAY:
                    out.attribute(name, (char[]) value);
                    break;

                case STRING_ARRAY:
                    out.attribute(name, (String[]) value);
                    break;

                case CLASS_ARRAY:
                    out.attribute(name, TypeArray.of((Class<?>[]) value).getNames());
                    break;

                case OBJECT:
                default:
                    out.attribute(name, value.toString());
                }
            } // for collection
        } // for field
    }

    private static ReflectRstDumper instance = new ReflectRstDumper();

    public static ReflectRstDumper getInstance() {
        return instance;
    }

}
