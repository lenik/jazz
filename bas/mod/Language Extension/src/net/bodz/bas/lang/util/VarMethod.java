package net.bodz.bas.lang.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import net.bodz.bas.lang.Control;
import net.bodz.bas.lang.err.IllegalUsageError;
import net.bodz.bas.nls.LangNLS;
import net.bodz.bas.types.HashTextMap;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TypesHierMap;
import net.bodz.bas.types.util.Types;

public class VarMethod extends _VMethod {

    private final String         name;

    /**
     * Parameter Types Map
     */
    private TypesHierMap<Method> ptmap;

    /**
     * @see Members#allMethods(Class, String)
     * @see Members#allMethods(Class, String,boolean)
     * @see Members#methods(Class, boolean)
     * @see Members#methods(Class, String, boolean)
     */
    public VarMethod(String name, Iterable<Method> methods) {
        this.name = name;
        ptmap = new TypesHierMap<Method>();
        for (Method method : methods)
            add(method);
    }

    public VarMethod(String name, Method method) {
        this.name = name;
        ptmap = new TypesHierMap<Method>();
        add(method);
    }

    public void add(Method method) {
        Class<?>[] pt = method.getParameterTypes();
        ptmap.put(pt, method);
    }

    public TypesHierMap<Method> getPTMap() {
        return ptmap;
    }

    public Method findMethod(Class<?>... paramTypes) {
        return ptmap.floor(paramTypes);
    }

    @Override
    public Object invokel(Object obj, Class<?>[] paramTypes, Object... params)
            throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {
        Method method = findMethod(paramTypes);
        if (method == null)
            throw new NoSuchMethodException(name + "(" //$NON-NLS-1$
                    + Types.joinNames(paramTypes) + ")"); //$NON-NLS-1$
        if (obj != null) {
            Class<?> objClass = obj.getClass();
            Class<?> declClass = method.getDeclaringClass();
            if (!declClass.isAssignableFrom(objClass))
                throw new IllegalUsageError(LangNLS.getString("VarMethod.incompatType") + objClass //$NON-NLS-1$
                        + LangNLS.getString("VarMethod.decl") + declClass); //$NON-NLS-1$
        }
        return Control.invoke(method, obj, params);
    }

    public static void classify(Iterable<Method> methods, Map<String, VarMethod> map) {
        TextMap<VarMethod> tmp = new HashTextMap<VarMethod>();
        for (Method m : methods) {
            String name = m.getName();
            VarMethod varm = tmp.get(name);
            if (varm == null) {
                varm = new VarMethod(name, m);
                tmp.put(name, varm);
            } else {
                varm.add(m);
            }
        }
    }

}
