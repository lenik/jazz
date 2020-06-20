package net.bodz.bas.t.variant;

import java.lang.reflect.Array;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.meta.source.FnHelper;

@FnHelper
public class _VariantFn {

    static String[] toStringArray(Object scalarOrArray) {
        if (scalarOrArray == null)
            return null;

        Class<?> type = scalarOrArray.getClass();
        if (!type.isArray())
            return new String[] { Nullables.toString(scalarOrArray) };

        if (type.getComponentType().equals(String.class))
            return (String[]) scalarOrArray;

        Object array = scalarOrArray;
        int len = Array.getLength(array);
        String[] sv = new String[len];
        for (int i = 0; i < len; i++) {
            Object item = Array.get(array, i);
            if (item != null) {
                sv[i] = item.toString();
            }
        }
        return sv;
    }

}
