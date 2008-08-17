package net.bodz.bas.cli;

import static net.bodz.bas.types.util.ArrayOps.Objects;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.bodz.bas.lang.Control;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.lang.script.ScriptException;
import net.bodz.bas.lang.script.ScriptMethod;
import net.bodz.bas.types.TypeParser;
import net.bodz.bas.types.TypeParsers;
import net.bodz.bas.types.util.Types;

public class MethodOption extends _Option<CallInfo> implements
        ScriptMethod<Object> {

    private final Method       method;
    private final int          argc;
    private final TypeParser[] parsers;

    public MethodOption(String name, Option option, Method method,
            OptionGroup optgrp) {
        super(name, option, CallInfo.class, optgrp);
        this.method = method;
        method.setAccessible(true); // ...

        Class<? extends TypeParser>[] wants = option.want();
        Class<?>[] types = method.getParameterTypes();
        argc = types.length;
        parsers = new TypeParser[argc];
        try {
            for (int i = 0; i < wants.length; i++)
                parsers[i] = Util.guessParser(Types.getClassInstance(wants[i]),
                        types[i]);
            for (int i = wants.length; i < argc; i++)
                parsers[i] = TypeParsers.guess(types[i]);
        } catch (CreateException e) {
            throw new CLIError(e.getMessage(), e);
        }
    }

    public Method getMethod() {
        return method;
    }

    @Override
    public int getParameterCount() {
        return argc;
    }

    public Object parseParameter(String param, int paramIndex)
            throws ParseException {
        if (parsers == null || paramIndex < 0 || paramIndex >= argc)
            throw new CLIError("param index out of bounds");
        Object val = parsers[paramIndex].parse(param);
        return val;
    }

    public Object call(Object object, String[] argv) throws ParseException,
            ScriptException {
        Object[] parameters = new Object[argc];
        for (int i = 0; i < argc; i++) {
            String arg = argv[i];
            parameters[i] = parseParameter(arg, i);
        }
        try {
            return Control.invoke(method, object, parameters);
        } catch (IllegalAccessException e) {
            throw new ScriptException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new ScriptException(e.getMessage(), e);
        }
    }

    // interface ScriptField

    private static final CallInfo NULL = new CallInfo(0);

    @Override
    public CallInfo get(Object object) throws ScriptException {
        return NULL;
    }

    @Override
    @Deprecated
    public void set(Object object, CallInfo info) throws ScriptException {
        info.returnValue = invoke(object, info.parameters);
    }

    // interface ScriptMethod

    @SuppressWarnings("unchecked")
    @Override
    public Class<Object> getReturnType() {
        return (Class<Object>) method.getReturnType();
    }

    private Class<?>[] argvTypes;

    @Override
    public Class<?>[] getParameterTypes() {
        if (argvTypes == null) {
            argvTypes = new Class<?>[argc];
            Objects.fill(argvTypes, String.class);
        }
        return argvTypes;
    }

    @Override
    public Object invoke(Object object, Object... parameters)
            throws ScriptException {
        String[] argv = new String[argc];
        Objects.copy(parameters, 0, argv, 0, argc);
        try {
            return call(object, argv);
        } catch (ParseException e) {
            throw new ScriptException(e.getMessage(), e);
        }
    }

}
