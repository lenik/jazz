package net.bodz.bas.commons.scripting;

public interface ScriptMethod<T> {

    Class<T> getReturnType();

    Class<?>[] getParameterTypes();

    T invoke(Object object, Object... parameters) throws IllegalArgumentException, ScriptException;

}
