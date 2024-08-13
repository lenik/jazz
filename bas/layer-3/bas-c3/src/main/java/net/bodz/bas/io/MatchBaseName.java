package net.bodz.bas.io;

import java.io.File;

public class MatchBaseName
        implements
            IPathPattern {

    final String baseName;
    // boolean wildcard;
    // Pattern namePattern;

    public MatchBaseName(String baseName) {
        if (baseName == null)
            throw new NullPointerException("baseName");
        this.baseName = baseName;
    }

    @Override
    public boolean accept(File dir, String name) {
        return baseName.equals(name);
    }

    @Override
    public boolean accept(File pathname) {
        return baseName.equals(pathname.getName());
    }

}
