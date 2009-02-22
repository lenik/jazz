package net.bodz.bas.mod;

public abstract class _Factory<T> implements Factory<T> {

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public _Factory() {
        this((Class<T>) Object.class);
    }

    public _Factory(Class<T> type) {
        this.type = type;
    }

    @Override
    public Class<T> getType() {
        return type;
    }

}
