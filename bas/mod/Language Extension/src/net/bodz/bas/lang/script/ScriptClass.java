package net.bodz.bas.lang.script;

public interface ScriptClass<T> {

    String getName();

    ScriptClass<?> getSuperClass();

    ScriptField<?>[] getFields();

    boolean hasField(String name);

    /** return null if not exists */
    <F> ScriptField<F> getField(String name);

    ScriptMethod<?>[] getMethods();

    boolean hasMethod(String name);

    /** return null if not exists */
    <R> ScriptMethod<R> getMethod(String name);

    T cast(Object obj);

}
