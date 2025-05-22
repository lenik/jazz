package net.bodz.bas.make.util;

import net.bodz.bas.meta.decl.NotNull;

public class NamedFloat
        extends NamedData<Float> {

    public NamedFloat() {
    }

    public NamedFloat(String key) {
        super(key);
    }

    public NamedFloat(String key, Float data) {
        super(key, data);
    }

    @NotNull
    @Override
    public Class<Float> getDataType() {
        return Float.class;
    }

    public static NamedFloat.Builder builder() {
        return new NamedFloat.Builder();
    }

    public static class Builder
            extends NamedData.Builder<Builder, Float> {
        public NamedFloat build() {
            return new NamedFloat(key, data);
        }
    }

}
