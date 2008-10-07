package net.bodz.bas.lang.ref;

public abstract class _Accessor<T> implements Accessor<T> {

    @Override
    public abstract T get();

    @Override
    public abstract void set(T value);

    @Override
    public abstract Class<?> getType();

    @Override
    public boolean isReadable() {
        return true;
    }

    @Override
    public boolean isWritable() {
        return true;
    }

}
