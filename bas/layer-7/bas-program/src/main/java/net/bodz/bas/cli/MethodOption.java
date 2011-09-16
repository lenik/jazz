package net.bodz.bas.cli;

import java.lang.reflect.Method;
import java.util.Arrays;

import javax.script.ScriptException;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;
import net.bodz.bas.meta.program.ArgsParseBy;
import net.bodz.bas.meta.program.OptionGroup;
import net.bodz.bas.traits.IParser;
import net.bodz.bas.traits.Traits;
import net.bodz.bas.util.type.SingletonUtil;

public class MethodOption
        extends _Option<CallInfo>
        implements ScriptMethod<Object> {

    private final Method method;
    private final int argc;
    private final IParser<?>[] parsers;

    public MethodOption(String name, Method method, OptionGroup optgrp) {
        super(name, method, CallInfo.class, optgrp);
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
                parsers[i] = Traits.getTraits(types[i], IParser.class);
            }
        } catch (CreateException e) {
            throw new CLIError(e);
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
        return Jdk7Reflect.invoke(method, object, parameters);
    }

    // interface ScriptField

    private static final CallInfo NULL = new CallInfo(0);

    @Override
    public CallInfo get(Object object)
            throws ReflectiveOperationException {
        return NULL;
    }

    @Override
    @Deprecated
    public void set(Object object, CallInfo info)
            throws ReflectiveOperationException {
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
            throw new ScriptException(e.getMessage(), e);
        }
    }

}
