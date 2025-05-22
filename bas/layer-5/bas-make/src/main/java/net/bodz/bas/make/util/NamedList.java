package net.bodz.bas.make.util;

import java.util.List;

import net.bodz.bas.meta.decl.NotNull;

public class NamedList<E>
        extends NamedData<List<E>> {

    public NamedList() {
    }

    public NamedList(String key) {
        super(key);
    }

    public NamedList(String key, List<E> data) {
        super(key, data);
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public Class<List<E>> getDataType() {
        return (Class<List<E>>) (Class<?>) List.class;
    }

    public static <E> NamedList.Builder<E> builder() {
        return new NamedList.Builder<E>();
    }

    public static class Builder<E>
            extends NamedData.Builder<Builder<E>, List<E>> {
        public NamedList<E> build() {
            return new NamedList<E>(key, data);
        }
    }

}
