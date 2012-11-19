package net.bodz.bas.cli.skel;

import java.lang.reflect.Modifier;
import java.util.*;
import java.util.Map.Entry;

import net.bodz.bas.c.java.util.Arrays;
import net.bodz.bas.err.IllegalUsageError;

class ValUtil {

    @SuppressWarnings("unchecked")
    public static <T> T addmulti(Class<?> type, T fieldobj, Object val)
            throws InstantiationException, IllegalAccessException {
        if (type.isArray())
            return Arrays.concat(type, fieldobj, val);
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

}
