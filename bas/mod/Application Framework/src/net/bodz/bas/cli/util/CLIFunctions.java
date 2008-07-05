package net.bodz.bas.cli.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.cli.CLIException;
import net.bodz.bas.cli.TypeParser;
import net.bodz.bas.cli.TypeParsers;
import net.bodz.bas.lang.Control;

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

    public CLIFunction wrap(Class<?> clazz, final Object obj,
            String methodName, Class<?>... argTypes)
            throws NoSuchMethodException {
        final Method method = clazz.getMethod(methodName, argTypes);
        final int argc = argTypes.length;
        final TypeParser<?>[] parsers = new TypeParser<?>[argc];
        for (int i = 0; i < argc; i++)
            parsers[i] = TypeParsers.guess(argTypes[i]);
        return new CLIFunction() {
            @Override
            public Object eval(String... args) throws Throwable {
                Object[] argv = new Object[argc];
                for (int i = 0; i < argc; i++) {
                    if (i >= args.length)
                        argv[i] = null;
                    else
                        argv[i] = parsers[i].parse(args[i]);
                }
                return Control.invoke(method, obj, argv);
            }
        };
    }

    public CLIFunction wrap(Class<?> clazz, String methodName,
            Class<?>... argTypes) throws NoSuchMethodException {
        return wrap(clazz, null, methodName, argTypes);
    }

    public CLIFunction wrap(Object obj, String methodName, Class<?>... argTypes)
            throws NoSuchMethodException {
        return wrap(obj.getClass(), obj, methodName, argTypes);
    }

    public static final CLIFunctions global;
    static {
        global = new CLIFunctions();
    }

}
