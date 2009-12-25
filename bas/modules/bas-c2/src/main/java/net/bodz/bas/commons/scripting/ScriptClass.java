package net.bodz.bas.commons.scripting;

import net.bodz.bas.api.hint.MetaData;

public interface ScriptClass<T>
        extends MetaData {

    String getName();

    ScriptClass<?> getSuperClass();

    ScriptField<?>[] getFields();

    boolean hasField(String name);

    /** return null if not exists */
    <F> ScriptField<F> getField(String name);

    Object get(Object object, String fieldName)
            throws ScriptException;

    void set(Object object, String fieldName, Object newValue)
            throws ScriptException;

    ScriptMethod<?>[] getMethods();

    boolean hasMethod(String name);

    /** return null if not exists */
    <R> ScriptMethod<R> getMethod(String name);

    Object invoke(Object object, String methodName, Object... parameters)
            throws ScriptException;

    T cast(Object object)
            throws ScriptException;

}
