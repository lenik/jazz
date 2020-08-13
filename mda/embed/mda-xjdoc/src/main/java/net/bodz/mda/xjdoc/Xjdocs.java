package net.bodz.mda.xjdoc;

import net.bodz.mda.xjdoc.taglib.ITagLibrary;
import net.bodz.mda.xjdoc.taglib.TagLibraryLoader;
import net.bodz.mda.xjdoc.taglib.TagLibrarySet;

public class Xjdocs {

    static IXjdocProvider defaultProvider = UnionXjdocProvider.getInstance();
    static TagLibrarySet defaultTagLibrary;

    public static IXjdocProvider getDefaultProvider() {
        return defaultProvider;
    }

    public static void setDefaultProvider(IXjdocProvider defaultProvider) {
        Xjdocs.defaultProvider = defaultProvider;
    }

    public static synchronized ITagLibrary getDefaultTagLibrary() {
        if (defaultTagLibrary == null) {
            ClassLoader classLoader = Xjdocs.class.getClassLoader();
            defaultTagLibrary = TagLibraryLoader.allFor(classLoader);
        }
        return defaultTagLibrary;
    }

    public static void setDefaultTagLibrary(TagLibrarySet defaultTagLibrary) {
        Xjdocs.defaultTagLibrary = defaultTagLibrary;
    }

}
