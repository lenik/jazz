package net.bodz.mda.xjdoc;

import net.bodz.mda.xjdoc.taglib.ITagLibrary;
import net.bodz.mda.xjdoc.taglib.TagLibraryLoader;
import net.bodz.mda.xjdoc.taglib.TagLibrarySet;

public class Xjdocs {

    public static IXjdocProvider getDefaultProvider() {
        return UnionXjdocProvider.getInstance();
    }

    public static ITagLibrary getDefaultTagLibrary() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        TagLibrarySet tagLibrarySet = TagLibraryLoader.allFor(classLoader);
        return tagLibrarySet;
    }

}
