package net.bodz.mda.xjdoc.model.conv;

import net.bodz.bas.text.flatf.FlatfLoader;

public class ClassDocLoader
        extends FlatfLoader {

    public ClassDocLoader(Class<?> contextClass) {
        this(contextClass.getPackage().getName());
    }

    public ClassDocLoader(String contextPackageName) {
        if (contextPackageName == null)
            throw new NullPointerException("contextPackageName");
    }

}
