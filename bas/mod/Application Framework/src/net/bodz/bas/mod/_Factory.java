package net.bodz.bas.mod;

public abstract class _Factory implements Factory {

    private final Class<?> type;

    public _Factory() {
        this(Object.class);
    }

    public _Factory(Class<?> type) {
        this.type = type;
    }

    @Override
    public Class<?> getType() {
        return type;
    }

}
