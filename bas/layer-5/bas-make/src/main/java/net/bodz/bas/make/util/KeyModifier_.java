package net.bodz.bas.make.util;

import net.bodz.bas.make.pattern.dtkey.IDataTypedParameterizedKeys;
import net.bodz.bas.meta.decl.NotNull;

public class KeyModifier_<T>
        implements IDataTypedParameterizedKeys<String, String, T> {

    String prefix;
    String suffix;
    Class<T> dataType;

    public KeyModifier_(String replacement, Class<T> dataType) {
        int hash = replacement.indexOf('%');
        if (hash == -1) {
            prefix = replacement;
            suffix = "";
        } else {
            prefix = replacement.substring(0, hash);
            suffix = replacement.substring(hash + 1);
        }
        this.dataType = dataType;
    }

    @NotNull
    @Override
    public Class<String> getParameterType() {
        return String.class;
    }

    @NotNull
    @Override
    public Class<String> getKeyType() {
        return String.class;
    }

    @NotNull
    @Override
    public Class<T> getDataType() {
        return dataType;
    }

    @Override
    public String getKey(String stem) {
        return prefix + stem + suffix;
    }

}
