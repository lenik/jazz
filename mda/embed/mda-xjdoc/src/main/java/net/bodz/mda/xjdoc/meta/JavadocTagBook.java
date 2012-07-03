package net.bodz.mda.xjdoc.meta;

import net.bodz.mda.xjdoc.util.ImportMap;

public class JavadocTagBook
        extends TagBook {

    ImportMap importMap;

    /**
     * 
     * In different stages, the type context is different:
     * <ul>
     * <li>For javadoc -> model, the context should be sourceFileImports. (
     * {@link ImportMap#normalize(String) expand} is used)
     * <li>For model -> ff, the context should be classImports. ( {@link ImportMap#add(String)
     * import} is used)
     * <li>For ff -> model, the context should be classImports. (
     * {@link ImportMap#normalize(String) expand} is used)
     * </ul>
     */
    public JavadocTagBook() {
        setTagType("author", DocTagType.INSTANCE.repeat());
        setTagType("exception", DocTagType.INSTANCE.typed());
        setTagType("param", DocTagType.INSTANCE.keyed());
        setTagType("return", DocTagType.INSTANCE);
        setTagType("throws", DocTagType.INSTANCE.typed());
    }

}
