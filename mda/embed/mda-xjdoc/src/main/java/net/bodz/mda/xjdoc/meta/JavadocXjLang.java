package net.bodz.mda.xjdoc.meta;

import net.bodz.mda.xjdoc.util.TypeNameContext;

public class JavadocXjLang
        extends XjLanguage {

    public JavadocXjLang(TypeNameContext typeNameContext) {
        setTagType("author", StringTagType.INSTANCE.repeat());
        setTagType("exception", DocTagType.INSTANCE.typed(typeNameContext)); // checked
        setTagType("param", DocTagType.INSTANCE.typed(typeNameContext));
        setTagType("return", DocTagType.INSTANCE);
        setTagType("throws", DocTagType.INSTANCE.keyed()); // unchecked
    }

}
