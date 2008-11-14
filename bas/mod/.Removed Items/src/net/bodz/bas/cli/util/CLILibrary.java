package net.bodz.bas.cli.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map.Entry;

import net.bodz.bas.cli.CLIException;
import net.bodz.bas.lang.Control;
import net.bodz.bas.lang.util.Members;
import net.bodz.bas.lang.util.VMethod;
import net.bodz.bas.lang.util.VarArgcMethod;
import net.bodz.bas.lang.util._VMethod;
import net.bodz.bas.text.interp.Quotable;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TextMap.HashTextMap;
import net.bodz.bas.types.TextMap.TreeTextMap;

@Deprecated
public class CLILibrary {

    private TextMap<VMethod> methods;
    private Object           context;

    public CLILibrary() {
        methods = new TreeTextMap<VMethod>();
    }

    public VMethod get(String name) {
        return methods.get(name);
    }

    public void addMethod(String name, VMethod func) {
        if (methods.containsKey(name))
            throw new IllegalArgumentException("cli function `" + name
                    + "' is already existed");
        methods.put(name, func);
    }

    /**
     * Merge all public methods of same name.
     * 
     * parameter of incompat type will be auto converted when invoking.
     * 
     * @see VarArgcMethod
     */
    public void addMethod(Class<?> clazz, final Object obj, String methodName)
            throws NoSuchMethodException {
        final VarArgcMethod vcMethod = new VarArgcMethod(methodName,//
                Members.publicMethods(clazz, methodName));
        VMethod wrapped = new _VMethod() {
            @Override
            public Object invokel(Object context, Class<?>[] paramTypes,
                    Object... params) throws NoSuchMethodException,
                    IllegalArgumentException, IllegalAccessException,
                    InvocationTargetException {
                /*
                 * paramTypes are String, so we just ignore it, let
                 * VarArgcMethod parse it.
                 */
                return vcMethod.invoke(obj, params);
                // return vcMethod.invokel(obj, paramTypes, params);
            }
        };
        addMethod(methodName, wrapped);
    }

    public void addMethod(Class<?> clazz, String methodName)
            throws NoSuchMethodException {
        addMethod(clazz, null, methodName);
    }

    public void addMethod(Object obj, String methodName)
            throws NoSuchMethodException {
        addMethod(obj.getClass(), obj, methodName);
    }

    public void addMethods(final Object obj) {
        Class<?> clazz = obj.getClass();
        Iterable<Method> mv = Members.publicMethods(clazz, Object.class);

        // TextMap<VarMethod> tmp = new HashTextMap<VarMethod>();
        TextMap<VarArgcMethod> tmp = new HashTextMap<VarArgcMethod>();
        VarArgcMethod.classify(mv, tmp);

        // wrap with obj/context
        for (Entry<String, VarArgcMethod> e : tmp.entrySet()) {
            String name = e.getKey();
            final VMethod varm = e.getValue();
            methods.put(name, new _VMethod() {
                @Override
                public Object invokel(Object context, Class<?>[] paramTypes,
                        Object... params) throws NoSuchMethodException,
                        IllegalArgumentException, IllegalAccessException,
                        InvocationTargetException {
                    /*
                     * paramTypes are String, so we just ignore it, let
                     * VarArgcMethod parse it.
                     */
                    return varm.invoke(obj, params);
                    // return varm.invokel(obj, paramTypes, params);
                }
            });
        }
    }

    public void removeMethod(String name) {
        methods.remove(name);
    }

    public Object invoke(String name, String... args) throws CLIException {
        VMethod m = get(name);
        if (m == null)
            throw new CLIException("cli function " + name + " isn't existed");
        try {
            return m._invoke(context, (Object[]) args);
        } catch (Control c) {
            throw c;
        } catch (Throwable e) {
            throw new CLIException("libfunction fail: " + name, e);
        }
    }

    static final Quotable spliter;
    static {
        spliter = new Quotable("\"\'".toCharArray());
    }

    public Object eval(String exp) throws CLIException {
        String[] s = spliter.splitDequote("\\s+", exp);
        String name = s[0];
        String[] args = new String[s.length - 1];
        for (int i = 0; i < args.length; i++)
            args[i] = s[i + 1];
        try {
            return invoke(name, args);
        } catch (Exception e) {
            throw new CLIException("failed to eval: " + exp, e);
        }
    }

}
