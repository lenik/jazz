package net.bodz.bas.lang.script;

public interface ScriptMethod<T> {

    Class<T> getReturnType();

    Class<?>[] getParameterTypes();

    T invoke(Object object, Object... parameters) throws IllegalArgumentException, ScriptException;

}
