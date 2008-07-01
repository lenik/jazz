package net.bodz.bas.lang.script;

public interface ScriptField<T> {

    Class<T> getType();

    T get(Object object) throws ScriptException;

    void set(Object object, T value) throws ScriptException;

}
