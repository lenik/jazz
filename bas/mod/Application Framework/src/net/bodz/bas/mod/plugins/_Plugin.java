package net.bodz.bas.mod.plugins;

import net.bodz.bas.annotations.ClassInfo;

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
