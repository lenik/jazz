package net.bodz.bas.make.util;

import net.bodz.bas.make.pattern.dtkey.IDataTypedParameterizedKeys;
import net.bodz.bas.meta.decl.NotNull;

public class GlobStringNames
        implements IDataTypedParameterizedKeys<String, String, String> {

    String prefix;
    String suffix;

    public GlobStringNames(String replacement) {
        int hash = replacement.indexOf('%');
        if (hash == -1) {
            prefix = replacement;
            suffix = "";
        } else {
            prefix = replacement.substring(0, hash);
            suffix = replacement.substring(hash + 1);
        }
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
    public Class<String> getDataType() {
        return String.class;
    }

    @Override
    public String getKey(String stem) {
        return prefix + stem + suffix;
    }

}
