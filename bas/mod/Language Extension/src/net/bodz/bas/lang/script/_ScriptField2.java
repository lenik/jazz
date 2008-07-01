package net.bodz.bas.lang.script;

/**
 * not used. 
 */
public abstract class _ScriptField2<T, E> implements ScriptField2<T, E> {

    @Override
    public T get(Object object) throws IndexOutOfBoundsException,
            ScriptException {
        return (T) get(object);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void set(Object object, T value) throws IndexOutOfBoundsException,
            ScriptException {
        set(object, (E) value);
    }

}
