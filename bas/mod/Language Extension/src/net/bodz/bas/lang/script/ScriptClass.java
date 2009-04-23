package net.bodz.bas.lang.script;

import net.bodz.bas.lang.Meta;

public interface ScriptClass<T> extends Meta {

    String getName();

    ScriptClass<?> getSuperClass();

    ScriptField<?>[] getFields();

    boolean hasField(String name);

    /** return null if not exists */
    <F> ScriptField<F> getField(String name);

    Object get(Object object, String fieldName) throws ScriptException;

    void set(Object object, String fieldName, Object newValue) throws ScriptException;

    ScriptMethod<?>[] getMethods();

    boolean hasMethod(String name);

    /** return null if not exists */
    <R> ScriptMethod<R> getMethod(String name);

    Object invoke(Object object, String methodName, Object... parameters) throws ScriptException;

    T cast(Object object) throws ScriptException;

}
