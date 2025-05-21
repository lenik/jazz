package net.bodz.bas.make.sample;

import net.bodz.bas.make.IParameterizedKeys;

public class GlobNames
        implements IParameterizedKeys<String, String> {

    String prefix;
    String suffix;

    public GlobNames(String replacement) {
        int hash = replacement.indexOf('%');
        if (hash == -1) {
            prefix = replacement;
            suffix = "";
        } else {
            prefix = replacement.substring(0, hash);
            suffix = replacement.substring(hash + 1);
        }
    }

    @Override
    public Class<String> getParameterType() {
        return String.class;
    }

    @Override
    public Class<String> getKeyType() {
        return String.class;
    }

    @Override
    public String getKey(String stem) {
        return prefix + stem + suffix;
    }

}
