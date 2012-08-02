package net.bodz.bas.util;

import net.bodz.bas.meta.build.AppClassDoc;

public class AbstractPlugin
        implements IPlugin {

    AppClassDoc classDoc;

    public AbstractPlugin() {
        AppClassDoc classDoc = AppClassDoc.get(getClass());
        description = classDoc.getText().toPlainText();

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
