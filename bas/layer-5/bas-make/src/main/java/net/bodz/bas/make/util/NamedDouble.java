package net.bodz.bas.make.util;

import net.bodz.bas.meta.decl.NotNull;

public class NamedDouble
        extends NamedData<Double> {

    public NamedDouble() {
    }

    public NamedDouble(String key) {
        super(key);
    }

    public NamedDouble(String key, Double data) {
        super(key, data);
    }

    @NotNull
    @Override
    public Class<Double> getDataType() {
        return Double.class;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder
            extends NamedData.Builder<Builder, Double> {
        public NamedDouble build() {
            return new NamedDouble(key, data);
        }
    }

}
