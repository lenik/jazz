package net.bodz.bas.cli;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.bodz.bas.lang.Control;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.lang.script.ScriptException;

public class MethodOption extends _Option<CallInfo> {

    private final Method          method;
    private final int             argc;
    private final TypeParser<?>[] parsers;

    public MethodOption(String name, Option option, Method method,
            OptionGroup optgrp) {
        super(name, option, CallInfo.class, optgrp);
        this.method = method;
        method.setAccessible(true);

        Class<? extends TypeParser<?>>[] wants = option.want();
        Class<?>[] types = method.getParameterTypes();
        argc = types.length;
        parsers = new TypeParser<?>[argc];
        for (int i = 0; i < wants.length; i++)
            parsers[i] = Util.guessParser(Util.getClassInstance(wants[i]),
                    types[i]);
        for (int i = wants.length; i < argc; i++)
            parsers[i] = TypeParsers.guess(types[i]);
    }

    private static final CallInfo NULL = new CallInfo(0);

    @Override
    public CallInfo get(Object object) throws ScriptException {
        return NULL;
    }

    @Override
    public void set(Object object, CallInfo info) throws ScriptException {
        try {
            info.retval = Control.invoke(method, object, info.argv);
        } catch (IllegalArgumentException e) {
            throw new ScriptException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new ScriptException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new ScriptException(e.getMessage(), e);
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

}
