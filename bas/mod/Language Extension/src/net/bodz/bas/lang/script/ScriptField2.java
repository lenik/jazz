package net.bodz.bas.lang.script;

public interface ScriptField2<T, E> extends ScriptField<T> {

    E get(Object object, Object... indexes) throws IndexOutOfBoundsException,
            ScriptException;

    void set(Object object, E value, Object... indexes)
            throws IndexOutOfBoundsException, ScriptException;

}
