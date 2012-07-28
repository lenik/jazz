package net.bodz.bas.util;

import net.bodz.bas.meta.build.AppClassDoc;
import net.bodz.mda.xjdoc.conv.ClassDocs;

public class AbstractPlugin
        implements IPlugin {

    AppClassDoc classDoc;

    public AbstractPlugin() {
        classDoc = ClassDocs.loadFromResource(getClass()).decorate(AppClassDoc.class);
    }

    @Override
    public String getDescription() {
        return classDoc.getTextHeader();
    }

    @Deprecated
    @Override
    public void initialize() {
    }

}
