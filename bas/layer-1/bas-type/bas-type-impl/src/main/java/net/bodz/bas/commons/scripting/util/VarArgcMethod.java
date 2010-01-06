package net.bodz.bas.commons.scripting.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import net.bodz.bas.exceptions.IllegalUsageError;
import net.bodz.bas.exceptions.ParseException;

public class VarArgcMethod
        extends _VMethod {

    private final String name;
    private IndexMap<Method> argcMap;
    private boolean autotype = true;

    public VarArgcMethod(String name) {
        this.name = name;
        this.argcMap = new IndexMap<Method>();
    }

    public VarArgcMethod(String name, Iterable<Method> methods) {
        this(name);
        for (Method method : methods)
            add(method);
    }

    public VarArgcMethod(String name, Method method) {
        this(name);
        add(method);
    }

    public Method get(int argc) {
        return argcMap.get(argc);
    }

    public void add(Method method) {
        Class<?>[] paramTypes = method.getParameterTypes();
        int index = paramTypes.length;
        if (argcMap.contains(index)) {
            Method m1 = argcMap.get(index);
            throw new IllegalUsageError("methods with same argc: \n" // 
                    + m1 + "\n" + method); 
        }
        argcMap.set(index, method);
    }

    /**
     * if parameter type incompat, TypeParser on the toString value is used.
     */
    @Override
    public Object invoke(Object obj, Object... params)
            throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        int argc = params.length;
        Method method = get(argc);
        if (method == null)
            throw new NoSuchMethodException(name + "([" + argc + "])");  
        if (autotype) {
            Class<?>[] declTypes = method.getParameterTypes();
            Class<?>[] actuTypes = Types.getTypes(params);
            Object[] argv = new Object[argc];
            for (int i = 0; i < argc; i++) {
                Class<?> dt = declTypes[i];
                Class<?> at = actuTypes[i];
                if (params[i] != null && !Types.box(dt).isAssignableFrom(at))
                    if (at == String.class) {
                        String stringArg = (String) params[i];
                        try {
                            argv[i] = TypeParsers.parse(dt, stringArg);
                        } catch (ParseException e) {
                            throw new IllegalArgumentException(e);
                        }
                    } else
                        // OR: <dt>parse(at.toString())
                        throw new IllegalArgumentException(
                                "invalid argument type: " + at + ", while "  
                                        + dt + " is expected. "); 
                else
                    argv[i] = params[i];
            }
            params = argv;
        }
        return Control.invoke(method, obj, params);
    }

    /**
     * safe-type invoke, the corresponding argc method must have compat signature.
     * 
     * @throws NoSuchMethodException
     *             if non-exist argc or the method signature incompat.
     */
    @Override
    public Object invokel(Object obj, Class<?>[] paramTypes, Object... params)
            throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        if (paramTypes != null)
            if (paramTypes.length != params.length)
                throw new IllegalArgumentException("number of param types differ to param vals"); 
        int argc = params.length;
        Method method = get(argc);
        if (method == null)
            throw new NoSuchMethodException("argc=" + argc); 
        if (paramTypes != null) {
            Class<?>[] declTypes = method.getParameterTypes();
            for (int i = 0; i < declTypes.length; i++) {
                Class<?> decl = declTypes[i];
                Class<?> actu = paramTypes[i];
                if (!decl.isAssignableFrom(actu))
                    throw new NoSuchMethodException("argc=" + paramTypes.length 
                            + " [" + i + "]: decl=" + decl + ", actu=" + actu);   
            }
        }
        return invoke(obj, params);
    }

    public static void classify(Iterable<Method> methods, Map<String, VarArgcMethod> map) {
        for (Method m : methods) {
            String name = m.getName();
            VarArgcMethod varm = map.get(name);
            if (varm == null) {
                varm = new VarArgcMethod(name, m);
                map.put(name, varm);
            } else {
                varm.add(m);
            }
        }
    }

}
