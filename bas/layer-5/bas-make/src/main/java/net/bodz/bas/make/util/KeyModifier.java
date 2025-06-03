package net.bodz.bas.make.util;

import net.bodz.bas.make.IParameterizedKey;

public class KeyModifier
        implements IParameterizedKey<String, String> {

    String prefix;
    String suffix;

    public KeyModifier(String replacement) {
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
