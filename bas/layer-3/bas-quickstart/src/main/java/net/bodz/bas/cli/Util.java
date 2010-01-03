package net.bodz.bas.cli;

import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

import net.bodz.bas.api.types.TypeParser;
import net.bodz.bas.commons.exceptions.ParseException;
import net.bodz.bas.commons.scripting.ScriptException;
import net.bodz.bas.nls.AppNLS;
import net.bodz.bas.type.feature.impl.TypeParsers;

class Util {

    @SuppressWarnings("unchecked")
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
    public static <T> T addmulti(Class<?> type, T fieldobj, Object val) throws ScriptException {
        if (type.isArray())
            return addarray(type, fieldobj, val);
        if (fieldobj == null)
            try {
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
                        throw new ScriptException(AppNLS.getString("Util.cantNewInst") //$NON-NLS-1$
                                + type);
                }
                fieldobj = (T) type.newInstance();
            } catch (InstantiationException e) {
                throw new ScriptException(e.getMessage(), e);
            } catch (IllegalAccessException e) {
                throw new ScriptException(e.getMessage(), e);
            }
        if (fieldobj instanceof Collection) {
            ((Collection<Object>) fieldobj).add(val);
        } else if (fieldobj instanceof Map) {
            Entry<Object, Object> entry = (Entry<Object, Object>) val;
            ((Map<Object, Object>) fieldobj).put(entry.getKey(), entry.getValue());

        }
        return fieldobj;
    }

    public static TypeParser guessParser(TypeParser parser, Class<?> clazz) throws ParseException {
        if (parser == null)
            parser = (TypeParser) TypeParsers.guess(clazz, true);
        return parser;
    }

    public static String dispval(Object o) {
        if (o == null)
            return AppNLS.getString("Util.null"); //$NON-NLS-1$
        Class<?> type = o.getClass();
        StringBuffer buf = null;
        if (type.isArray()) {
            int l = Array.getLength(o);
            buf = new StringBuffer(l * 20);
            buf.append("{"); //$NON-NLS-1$
            for (int i = 0; i < l; i++) {
                if (i > 0)
                    buf.append(", "); //$NON-NLS-1$
                buf.append(dispval(Array.get(o, i)));
            }
            buf.append("}"); //$NON-NLS-1$
        } else if (o instanceof Collection<?>) {
            Collection<?> col = (Collection<?>) o;
            buf = new StringBuffer(col.size() * 20);
            buf.append(type.getSimpleName() + " {"); //$NON-NLS-1$
            boolean first = true;
            for (Object c : col) {
                if (first)
                    first = false;
                else
                    buf.append(", "); //$NON-NLS-1$
                buf.append(dispval(c));
            }
            buf.append('}');
        } else if (o instanceof Map<?, ?>) {
            Map<?, ?> map = (Map<?, ?>) o;
            buf = new StringBuffer(map.size() * 20);
            buf.append(type.getSimpleName() + " {"); //$NON-NLS-1$
            boolean first = true;
            for (Map.Entry<?, ?> e : map.entrySet()) {
                if (first)
                    first = false;
                else
                    buf.append(", "); //$NON-NLS-1$
                buf.append(e.getKey() + "=" + dispval(e.getValue())); //$NON-NLS-1$
            }
            buf.append('}');
        } else if (o instanceof String) {
            return "\"" + o + "\""; //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            String typeName = o.getClass().getName();
            int dot = typeName.lastIndexOf('.');
            if (dot != -1)
                typeName = typeName.substring(dot + 1);
            return typeName + "(" + String.valueOf(o) + ")"; //$NON-NLS-1$ //$NON-NLS-2$
        }
        return buf == null ? "" : buf.toString(); //$NON-NLS-1$
    }

    private static Map<Class<?>, Object> trueValues;
    static {
        trueValues = new HashMap<Class<?>, Object>();
        trueValues.put(byte.class, (byte) 1);
        trueValues.put(Byte.class, (byte) 1);
        trueValues.put(short.class, (short) 1);
        trueValues.put(Short.class, (short) 1);
        trueValues.put(int.class, (int) 1);
        trueValues.put(Integer.class, (int) 1);
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
        trueValues.put(String.class, ""); //$NON-NLS-1$
    }

    public static Object getTrueValue(Class<?> type) {
        return trueValues.get(type);
    }

}
