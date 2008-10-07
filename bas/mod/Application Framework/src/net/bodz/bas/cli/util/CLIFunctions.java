package net.bodz.bas.cli.util;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.cli.CLIException;
import net.bodz.bas.lang.Control;
import net.bodz.bas.lang.util.Members;
import net.bodz.bas.lang.util.VarArgcMethod;

public class CLIFunctions {

    private Map<String, CLIFunction> registry;

    public CLIFunctions() {
        registry = new HashMap<String, CLIFunction>();
    }

    public CLIFunction get(String name) {
        return registry.get(name);
    }

    public void register(String name, CLIFunction func) {
        if (registry.containsKey(name))
            throw new IllegalArgumentException("cli function `" + name
                    + "' is already existed");
        registry.put(name, func);
    }

    public void unregister(String name) {
        registry.remove(name);
    }

    public Object invoke(String name, String... args) throws CLIException {
        CLIFunction func = get(name);
        if (func == null)
            throw new CLIException("cli function " + name + " isn't existed");
        try {
            return func.eval(args);
        } catch (Control c) {
            throw c;
        } catch (Throwable e) {
            throw new CLIException(e.getMessage(), e);
        }
    }

    public Object eval(String exp) throws CLIException {
        String[] s = exp.split("\\|");
        String name = s[0];
        String[] args = new String[s.length - 1];
        for (int i = 0; i < args.length; i++)
            args[i] = s[i + 1];
        return invoke(name, args);
    }

    public CLIFunction wrap(Class<?> clazz, final Object obj, String methodName)
            throws NoSuchMethodException {
        final VarArgcMethod method = new VarArgcMethod(methodName,//
                Members.publicMethods(clazz, methodName));
        return new CLIFunction() {
            @Override
            public Object eval(String... args) throws Throwable {
                return method.invoke(obj, (Object[]) args);
            }
        };
    }

    public CLIFunction wrap(Class<?> clazz, String methodName)
            throws NoSuchMethodException {
        return wrap(clazz, null, methodName);
    }

    public CLIFunction wrap(Object obj, String methodName)
            throws NoSuchMethodException {
        return wrap(obj.getClass(), obj, methodName);
    }

    public static final CLIFunctions global;
    static {
        global = new CLIFunctions();
    }

}
