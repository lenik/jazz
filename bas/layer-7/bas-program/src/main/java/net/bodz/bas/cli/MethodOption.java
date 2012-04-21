package net.bodz.bas.cli;

import java.lang.reflect.Method;
import java.util.Arrays;

import net.bodz.bas.c.type.SingletonUtil;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.program.ArgsParseBy;
import net.bodz.bas.meta.program.OptionGroup;
import net.bodz.bas.potato.traits.IMethod;
import net.bodz.bas.trait.Traits;
import net.bodz.bas.traits.IParser;

public class MethodOption
        extends _Option<MethodCall>
        implements IMethod {

    private final Method method;
    private final int argc;
    private final IParser<?>[] parsers;

    public MethodOption(String name, Method method, OptionGroup optgrp)
            throws ReflectiveOperationException {
        super(name, method, MethodCall.class, optgrp);
        this.method = method;
        method.setAccessible(true); // ...

        ArgsParseBy argsParseBy = method.getAnnotation(ArgsParseBy.class);
        Class<? extends IParser<?>>[] wants = _c0;
        String[] wantParams = null;
        if (argsParseBy != null) {
            wants = argsParseBy.value();
            wantParams = argsParseBy.param();
        }
        Class<?>[] types = method.getParameterTypes();
        argc = types.length;
        parsers = new IParser<?>[argc];
        try {
            for (int i = 0; i < wants.length; i++) {
                IParser<?> parserInst;
                if (i < wantParams.length)
                    parserInst = SingletonUtil.getClassInstance(wants[i], //
                            wantParams[i]);
                else
                    parserInst = SingletonUtil.getClassInstance(wants[i]);
                parsers[i] = Util.guessParser(parserInst, types[i]);
            }
            for (int i = wants.length; i < argc; i++) {
                parsers[i] = Traits.getTrait(types[i], IParser.class);
            }
        } catch (ParseException e) {
            throw new CLIError(e);
        }
    }

    private static final Class<? extends IParser<?>>[] _c0;
    static {
        @SuppressWarnings("unchecked")
        //
        Class<? extends IParser<?>>[] c0 = (Class<? extends IParser<?>>[]) new Class<?>[0];
        _c0 = c0;
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

    public Object call(Object object, String[] argv)
            throws ParseException, ReflectiveOperationException {
        Object[] parameters = new Object[argc];
        for (int i = 0; i < argc; i++) {
            String arg = argv[i];
            parameters[i] = parseParameter(arg, i);
        }
        return method.invoke(object, parameters);
    }

    // interface ScriptField

    private static final MethodCall NULL = new MethodCall(0);

    public MethodCall get(Object object)
            throws ReflectiveOperationException {
        return NULL;
    }

    @Deprecated
    public void set(Object object, MethodCall call)
            throws ReflectiveOperationException {
        call.returnValue = invoke(object, call.parameters);
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
            Arrays.fill(argvTypes, String.class);
        }
        return argvTypes;
    }

    @Override
    public Object invoke(Object object, Object... parameters)
            throws ReflectiveOperationException {
        String[] argv = new String[argc];
        System.arraycopy(parameters, 0, argv, 0, argc);
        try {
            return call(object, argv);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    @Override
    public Object invokeStatic(Object... parameters)
            throws ReflectiveOperationException {
        throw new NoSuchMethodException();
    }

}
