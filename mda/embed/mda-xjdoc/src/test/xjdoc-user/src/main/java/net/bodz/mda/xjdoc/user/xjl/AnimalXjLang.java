package net.bodz.mda.xjdoc.user.xjl;

import net.bodz.mda.xjdoc.meta.DocTagType;
import net.bodz.mda.xjdoc.meta.JavadocXjLang;
import net.bodz.mda.xjdoc.util.TypeNameContext;

public class AnimalXjLang
        extends JavadocXjLang {

    public AnimalXjLang(TypeNameContext typeNameContext) {
        super(typeNameContext);
        setTagType("color", DocTagType.INSTANCE);
    }

}
