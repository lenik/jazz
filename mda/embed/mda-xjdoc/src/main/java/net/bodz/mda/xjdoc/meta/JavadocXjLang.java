package net.bodz.mda.xjdoc.meta;

import net.bodz.mda.xjdoc.util.TypeNameContext;

public class JavadocXjLang
        extends XjLanguage {

    /**
     * 
     * In different stages, the type context is different:
     * <ul>
     * <li>For javadoc -> model, the context should be sourceFileImports. (
     * {@link TypeNameContext#expand(String) expand} is used)
     * <li>For model -> ff, the context should be classImports. (
     * {@link TypeNameContext#importTypeName(String) import} is used)
     * <li>For ff -> model, the context should be classImports. (
     * {@link TypeNameContext#expand(String) expand} is used)
     * </ul>
     */
    public JavadocXjLang(TypeNameContext typeNameContext) {
        // this.typeNameContext = typeNameContext;
        setTagType("author", StringTagType.INSTANCE.repeat());
        setTagType("exception", DocTagType.INSTANCE.typed(typeNameContext)); // checked
        setTagType("param", DocTagType.INSTANCE.keyed());
        setTagType("return", DocTagType.INSTANCE);
        setTagType("throws", DocTagType.INSTANCE.typed(typeNameContext)); // unchecked
    }

}
