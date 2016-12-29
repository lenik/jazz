package net.bodz.mda.xjdoc;

import net.bodz.mda.xjdoc.taglib.ITagLibrary;
import net.bodz.mda.xjdoc.taglib.TagLibraryLoader;
import net.bodz.mda.xjdoc.taglib.TagLibrarySet;

public class Xjdocs {

    static IXjdocProvider defaultProvider = UnionXjdocProvider.getInstance();

    public static IXjdocProvider getDefaultProvider() {
        return defaultProvider;
    }

    public static void setDefaultProvider(IXjdocProvider defaultProvider) {
        Xjdocs.defaultProvider = defaultProvider;
    }

    public static ITagLibrary getDefaultTagLibrary() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        TagLibrarySet tagLibrarySet = TagLibraryLoader.allFor(classLoader);
        return tagLibrarySet;
    }

}
