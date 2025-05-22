package net.bodz.bas.make.util;

import net.bodz.bas.meta.decl.NotNull;

public class NamedLong
        extends NamedData<Long> {

    public NamedLong() {
    }

    public NamedLong(String key) {
        super(key);
    }

    public NamedLong(String key, Long data) {
        super(key, data);
    }

    @NotNull
    @Override
    public Class<Long> getDataType() {
        return Long.class;
    }

    public static NamedLong.Builder builder() {
        return new NamedLong.Builder();
    }

    public static class Builder
            extends NamedData.Builder<Builder, Long> {
        public NamedLong build() {
            return new NamedLong(key, data);
        }
    }

}
