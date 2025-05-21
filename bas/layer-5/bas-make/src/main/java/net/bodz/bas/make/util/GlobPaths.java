package net.bodz.bas.make.util;

import java.nio.file.Path;
import java.nio.file.Paths;

import net.bodz.bas.make.IParameterizedKeys;

public class GlobPaths
        implements IParameterizedKeys<String, Path> {

    String prefix;
    String suffix;

    public GlobPaths(String replacement) {
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
    public Class<Path> getKeyType() {
        return Path.class;
    }

    @Override
    public Path getKey(String parameter) {
        String path = prefix + parameter + suffix;
        return Paths.get(path);
    }

}
