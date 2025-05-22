package net.bodz.bas.make.util;

import net.bodz.bas.meta.decl.NotNull;

public class NamedInteger
        extends NamedData<Integer> {

    public NamedInteger() {
    }

    public NamedInteger(String key) {
        super(key);
    }

    public NamedInteger(String key, Integer data) {
        super(key, data);
    }

    @NotNull
    @Override
    public Class<Integer> getDataType() {
        return Integer.class;
    }

    public static NamedInteger.Builder builder() {
        return new NamedInteger.Builder();
    }

    public static class Builder
            extends NamedData.Builder<Builder, Integer> {
        public NamedInteger build() {
            return new NamedInteger(key, data);
        }
    }

}
