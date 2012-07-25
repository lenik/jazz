package net.bodz.bas.util;

import net.bodz.bas.meta.build.AppClassDoc;

public class AbstractPlugin
        implements IPlugin {

    public AbstractPlugin() {
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
