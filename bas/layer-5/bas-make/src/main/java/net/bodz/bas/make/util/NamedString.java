package net.bodz.bas.make.util;

import net.bodz.bas.meta.decl.NotNull;

public class NamedString
        extends NamedData<String> {

    public NamedString() {
    }

    public NamedString(String key) {
        super(key);
    }

    public NamedString(String key, String data) {
        super(key, data);
    }

    @NotNull
    @Override
    public Class<String> getDataType() {
        return String.class;
    }

    public static NamedString.Builder builder() {
        return new NamedString.Builder();
    }

    public static class Builder
            extends NamedData.Builder<Builder, String> {
        public NamedString build() {
            return new NamedString(key, data);
        }
    }

}
