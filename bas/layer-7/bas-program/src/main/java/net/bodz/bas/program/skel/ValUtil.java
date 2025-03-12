package net.bodz.bas.program.skel;

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

import net.bodz.bas.c.java.util.Arrays;
import net.bodz.bas.err.IllegalUsageError;

class ValUtil {

    @SuppressWarnings("unchecked")
    public static <T> T addmulti(Class<?> type, T fieldobj, Object val)
            throws ReflectiveOperationException, IllegalAccessException {
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
                    throw new IllegalUsageError(
                            "don\'t know how to create new instance of abstract class/interface " + type);
            }
            fieldobj = (T) type.getConstructor().newInstance();
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
