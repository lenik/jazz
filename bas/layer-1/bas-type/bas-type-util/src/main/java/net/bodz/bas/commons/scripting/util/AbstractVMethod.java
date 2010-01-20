package net.bodz.bas.commons.scripting.util;

import net.bodz.bas.jdk6compat.jdk7emul.IllegalAccessException;
import net.bodz.bas.jdk6compat.jdk7emul.InvocationTargetException;
import net.bodz.bas.jdk6compat.jdk7emul.NoSuchMethodException;
import net.bodz.bas.type.util.TypeArray;

public abstract class AbstractVMethod
        implements VMethod {

    public Object invoke(Object obj, Object... params)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class<?>[] paramTypes = TypeArray.getClasses(params);
        return invokel(obj, paramTypes, params);
    }

}
