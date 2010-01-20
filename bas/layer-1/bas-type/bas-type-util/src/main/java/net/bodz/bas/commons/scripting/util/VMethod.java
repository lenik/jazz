package net.bodz.bas.commons.scripting.util;

import net.bodz.bas.jdk6compat.jdk7emul.IllegalAccessException;
import net.bodz.bas.jdk6compat.jdk7emul.InvocationTargetException;
import net.bodz.bas.jdk6compat.jdk7emul.NoSuchMethodException;

public interface VMethod {

    Object invokel(Object obj, Class<?>[] paramTypes, Object... params)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

    Object invoke(Object obj, Object... params)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

}
