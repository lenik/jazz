package net.bodz.bas.valtype.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sio.indent.IIndention;

public class TypeChain {

    public static Class<?>[] listSuperLast(Class<?> clazz) {
        List<Class<?>> list = new ArrayList<Class<?>>();
        while (clazz != null) {
            list.add(clazz);
            clazz = clazz.getSuperclass();
        }
        return list.toArray(ArrayUtils.EMPTY_CLASS_ARRAY);
    }

    public static Class<?>[] listSuperFirst(Class<?> clazz) {
        Class<?>[] list = listSuperLast(clazz);
        ArrayUtils.reverse(list);
        return list;
    }

    public static <T extends IPrintOut & IIndention> void dumpTypeTree(Class<?> type, T out) {
        dumpTypeTree(type, out, false);
    }

    public static <T extends IPrintOut & IIndention> void dumpTypeTree(Class<?> type, T out,
            boolean removeDupInterfaces) {
        if (type == null)
            throw new NullPointerException("type");
        if (out == null)
            throw new NullPointerException("out");
        out.println(TypeName.pretty(type));
        out.increaseIndentLevel();

        Class<?> superclass = type.getSuperclass();
        if (superclass != null)
            dumpTypeTree(superclass, out, removeDupInterfaces);

        Class<?>[] interfaces = type.getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            Class<?> iface = interfaces[i];
            out.println(TypeName.pretty(iface));
            dumpTypeTree(iface, out, removeDupInterfaces);
        }

        out.decreaseIndentLevel();
    }

}
