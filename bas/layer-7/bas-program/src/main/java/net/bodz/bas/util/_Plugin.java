package net.bodz.bas.util;

import net.bodz.bas.meta.build.AppClassDoc;

public class _Plugin implements Plugin {

    private final String description;

    public _Plugin() {
        AppClassDoc info = AppClassDoc.get(getClass());
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
