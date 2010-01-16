package net.bodz.bas.commons.scripting;

public interface ScriptField<T> {

    String getName();

    Class<T> getType();

    T get(Object object) throws ScriptException;

    void set(Object object, T value) throws ScriptException;

}
