package net.bodz.bas.cli;

import java.lang.reflect.Method;
import java.util.Arrays;

import net.bodz.bas.cli.annotations.ArgsParseBy;
import net.bodz.bas.cli.annotations.OptionGroup;
import net.bodz.bas.commons.scripting.ScriptException;
import net.bodz.bas.commons.scripting.ScriptMethod;
import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;
import net.bodz.bas.type.traits.IParser;
import net.bodz.bas.util.exception.CreateException;
import net.bodz.bas.util.exception.ParseException;
import net.bodz.bas.valtype.util.ClassInstance;

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
                    parserInst = ClassInstance.getClassInstance(wants[i], //
                            wantParams[i]);
                else
                    parserInst = ClassInstance.getClassInstance(wants[i]);
                parsers[i] = Util.guessParser(parserInst, types[i]);
            }
            for (int i = wants.length; i < argc; i++) {
                parsers[i] = TypeParsers.guess(types[i], true);
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
            throws ParseException, ScriptException {
        Object[] parameters = new Object[argc];
        for (int i = 0; i < argc; i++) {
            String arg = argv[i];
            parameters[i] = parseParameter(arg, i);
        }
        try {
            return Jdk7Reflect.invoke(method, object, parameters);
        } catch (ReflectiveOperationException e) {
            throw new ScriptException(e.getMessage(), e);
        }
    }

    // interface ScriptField

    private static final CallInfo NULL = new CallInfo(0);

    @Override
    public CallInfo get(Object object)
            throws ScriptException {
        return NULL;
    }

    @Override
    @Deprecated
    public void set(Object object, CallInfo info)
            throws ScriptException {
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
            throws ScriptException {
        String[] argv = new String[argc];
        System.arraycopy(parameters, 0, argv, 0, argc);
        try {
            return call(object, argv);
        } catch (ParseException e) {
            throw new ScriptException(e.getMessage(), e);
        }
    }

}
