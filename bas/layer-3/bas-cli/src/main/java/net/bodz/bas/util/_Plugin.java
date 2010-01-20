package net.bodz.bas.util;

import net.bodz.bas.a.ClassInfo;

public class _Plugin implements Plugin {

    private final String description;

    public _Plugin() {
        ClassInfo info = ClassInfo.get(getClass());
        description = info.getDoc();
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Deprecated
    @Override
    public void initialize() {
    }

}
