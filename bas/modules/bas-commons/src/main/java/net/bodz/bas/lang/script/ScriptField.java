package net.bodz.bas.lang.script;

public interface ScriptField<T> {

    String getName();

    Class<T> getType();

    T get(Object object) throws ScriptException;

    void set(Object object, T value) throws ScriptException;

}
