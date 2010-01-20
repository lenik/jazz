package net.bodz.bas.type.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.bodz.bas.sio.ILineCharOut;
import net.bodz.bas.sio.indent.IIndention;

public class TypeChain {

    public static Class<?>[] list(Class<?> clazz) {
        return list(clazz, false);
    }

    public static Class<?>[] list(Class<?> clazz, boolean rootFirst) {
        List<Class<?>> list = new ArrayList<Class<?>>();
        while (clazz != null) {
            list.add(clazz);
            clazz = clazz.getSuperclass();
        }
        if (rootFirst)
            Collections.reverse(list);
        return list.toArray(new Class<?>[0]);
    }

    public static <T extends ILineCharOut & IIndention> void dumpTypeTree(Class<?> type, T out) {
        dumpTypeTree(type, out, false);
    }

    public static <T extends ILineCharOut & IIndention> void dumpTypeTree(Class<?> type, T out,
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
