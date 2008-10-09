package net.bodz.bas.lang.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.bodz.bas.lang.Control;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.lang.err.IllegalUsageError;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.lang.err.ReflectException;
import net.bodz.bas.types.IndexMap;
import net.bodz.bas.types.TypeParser;
import net.bodz.bas.types.TypeParsers;
import net.bodz.bas.types.util.Types;

public class VarArgcMethod {

    private final String     name;
    private IndexMap<Method> argcMap;
    private boolean          autotype = true;

    public VarArgcMethod(String name, Iterable<Method> methods) {
        this.name = name;
        this.argcMap = new IndexMap<Method>();
        for (Method method : methods) {
            Class<?>[] paramTypes = method.getParameterTypes();
            int index = paramTypes.length;
            if (argcMap.contains(index)) {
                Method m1 = argcMap.get(index);
                throw new IllegalUsageError("methods with same argc: \n" //
                        + m1 + "\n" + method);
            }
            argcMap.set(index, method);
        }
    }

    public Method get(int argc) {
        return argcMap.get(argc);
    }

    public Object invoke(Object obj, Object... args)
            throws NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException {
        int argc = args.length;
        Method method = get(argc);
        if (method == null)
            throw new NoSuchMethodException(name + "([" + argc + "])");
        if (autotype) {
            Class<?>[] declTypes = method.getParameterTypes();
            Class<?>[] actuTypes = Types.getTypes(args);
            Object[] argv = new Object[argc];
            for (int i = 0; i < argc; i++) {
                Class<?> dt = declTypes[i];
                Class<?> at = actuTypes[i];
                if (argv[i] != null && !Types.box(dt).isAssignableFrom(at))
                    if (at == String.class) {
                        String stringArg = (String) args[i];
                        try {
                            TypeParser parser = TypeParsers.guess(dt);
                            Object parsed = parser.parse(stringArg);
                            argv[i] = parsed;
                        } catch (CreateException e) {
                            throw new IllegalArgumentException(e);
                        } catch (ParseException e) {
                            throw new IllegalArgumentException(e);
                        }
                    } else
                        // OR: <dt>parse(at.toString())
                        throw new IllegalArgumentException(
                                "invalid argument type: " + at + ", while "
                                        + dt + " is expected. ");
                else
                    argv[i] = args[i];
            }
            args = argv;
        }
        return Control.invoke(method, obj, args);
    }

    public Object _invoke(Object obj, Object... args) {
        try {
            return invoke(obj, args);
        } catch (IllegalArgumentException e) {
            throw new ReflectException(e);
        } catch (NoSuchMethodException e) {
            throw new ReflectException(e);
        } catch (IllegalAccessException e) {
            throw new ReflectException(e);
        } catch (InvocationTargetException e) {
            throw new ReflectException(e);
        }
    }

}
