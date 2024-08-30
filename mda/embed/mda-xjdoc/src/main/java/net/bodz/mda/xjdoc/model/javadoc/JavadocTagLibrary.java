package net.bodz.mda.xjdoc.model.javadoc;

import net.bodz.mda.xjdoc.taglib.AbstractTagLibrary;
import net.bodz.mda.xjdoc.tagtype.AuthorsTag;
import net.bodz.mda.xjdoc.tagtype.HeadedTextTag;
import net.bodz.mda.xjdoc.tagtype.LocaleTextTag;
import net.bodz.mda.xjdoc.tagtype.QNameHeadedTextTag;
import net.bodz.mda.xjdoc.tagtype.StringTag;
import net.bodz.mda.xjdoc.tagtype.TextTag;

public class JavadocTagLibrary
        extends AbstractTagLibrary {

    public JavadocTagLibrary() {
        addTagType("author", AuthorsTag.class);
        addTagType("exception", QNameHeadedTextTag.class);
        addTagType("label",  LocaleTextTag.class);
        addTagType("param",  HeadedTextTag.class);
        addTagType("return",  TextTag.class);
        addTagType("see",  StringTag.class);
        addTagType("throws",  QNameHeadedTextTag.class);
    }

}
