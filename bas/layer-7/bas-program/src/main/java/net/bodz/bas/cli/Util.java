package net.bodz.bas.cli;

import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.traits.IParser;
import net.bodz.bas.traits.Traits;

class Util {

    public static <T> T addarray(Class<?> type, T fieldobj, Object val) {
        Class<?> valtype = type.getComponentType();
        int index;
        if (fieldobj == null) {
            fieldobj = (T) Array.newInstance(valtype, 1);
            index = 0;
        } else {
            int len = Array.getLength(fieldobj);
            T newarray = (T) Array.newInstance(valtype, len + 1);
            System.arraycopy(fieldobj, 0, newarray, 0, len);
            fieldobj = newarray;
            index = len;
        }
        Array.set(fieldobj, index, val);
        return fieldobj;
    }

    @SuppressWarnings("unchecked")
    public static <T> T addmulti(Class<?> type, T fieldobj, Object val)
            throws InstantiationException, IllegalAccessException {
        if (type.isArray())
            return addarray(type, fieldobj, val);
        if (fieldobj == null) {
            if (type.isInterface() || Modifier.isAbstract(type.getModifiers())) {
                if (List.class.isAssignableFrom(type))
                    type = ArrayList.class;
                else if (SortedSet.class.isAssignableFrom(type))
                    type = TreeSet.class;
                else if (Set.class.isAssignableFrom(type))
                    type = HashSet.class;
                else if (Collection.class.isAssignableFrom(type))
                    type = ArrayList.class;
                else if (SortedMap.class.isAssignableFrom(type))
                    type = TreeMap.class;
                else if (Map.class.isAssignableFrom(type))
                    type = HashMap.class;
                else
                    throw new IllegalUsageError("don\'t know how to create new instance of abstract class/interface "
                            + type);
            }
            fieldobj = (T) type.newInstance();
        }
        if (fieldobj instanceof Collection) {
            ((Collection<Object>) fieldobj).add(val);
        } else if (fieldobj instanceof Map) {
            Entry<Object, Object> entry = (Entry<Object, Object>) val;
            ((Map<Object, Object>) fieldobj).put(entry.getKey(), entry.getValue());

        }
        return fieldobj;
    }

    public static IParser<?> guessParser(IParser<?> parser, Class<?> clazz)
            throws ParseException {
        if (parser == null)
            parser = Traits.getTraits(clazz, IParser.class);
        return parser;
    }

    public static String dispval(Object o) {
        if (o == null)
            return "null";
        Class<?> type = o.getClass();
        StringBuilder buf = null;
        if (type.isArray()) {
            int l = Array.getLength(o);
            buf = new StringBuilder(l * 20);
            buf.append("{");
            for (int i = 0; i < l; i++) {
                if (i > 0)
                    buf.append(", ");
                buf.append(dispval(Array.get(o, i)));
            }
            buf.append("}");
        } else if (o instanceof Collection<?>) {
            Collection<?> col = (Collection<?>) o;
            buf = new StringBuilder(col.size() * 20);
            buf.append(type.getSimpleName() + " {");
            boolean first = true;
            for (Object c : col) {
                if (first)
                    first = false;
                else
                    buf.append(", ");
                buf.append(dispval(c));
            }
            buf.append('}');
        } else if (o instanceof Map<?, ?>) {
            Map<?, ?> map = (Map<?, ?>) o;
            buf = new StringBuilder(map.size() * 20);
            buf.append(type.getSimpleName() + " {");
            boolean first = true;
            for (Map.Entry<?, ?> e : map.entrySet()) {
                if (first)
                    first = false;
                else
                    buf.append(", ");
                buf.append(e.getKey() + "=" + dispval(e.getValue()));
            }
            buf.append('}');
        } else if (o instanceof String) {
            return "\"" + o + "\"";
        } else {
            String typeName = o.getClass().getName();
            int dot = typeName.lastIndexOf('.');
            if (dot != -1)
                typeName = typeName.substring(dot + 1);
            return typeName + "(" + String.valueOf(o) + ")";
        }
        return buf == null ? "" : buf.toString();
    }

    private static Map<Class<?>, Object> trueValues;
    static {
        trueValues = new HashMap<Class<?>, Object>();
        trueValues.put(byte.class, (byte) 1);
        trueValues.put(Byte.class, (byte) 1);
        trueValues.put(short.class, (short) 1);
        trueValues.put(Short.class, (short) 1);
        trueValues.put(int.class, 1);
        trueValues.put(Integer.class, 1);
        trueValues.put(long.class, (long) 1);
        trueValues.put(Long.class, (long) 1);
        trueValues.put(float.class, (float) 1);
        trueValues.put(Float.class, (float) 1);
        trueValues.put(double.class, (double) 1);
        trueValues.put(Double.class, (double) 1);
        trueValues.put(boolean.class, true);
        trueValues.put(Boolean.class, true);
        trueValues.put(char.class, '1');
        trueValues.put(Character.class, '1');
        trueValues.put(String.class, "");
    }

    public static Object getTrueValue(Class<?> type) {
        return trueValues.get(type);
    }

}
