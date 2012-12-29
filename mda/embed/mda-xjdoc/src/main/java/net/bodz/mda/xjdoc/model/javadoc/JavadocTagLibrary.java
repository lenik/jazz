package net.bodz.mda.xjdoc.model.javadoc;

import net.bodz.mda.xjdoc.taglib.TagLibrary;
import net.bodz.mda.xjdoc.tagtype.DocTagType;

public class JavadocTagLibrary
        extends TagLibrary {

    {
        setTagType("author", DocTagType.getInstance().repeat());
        setTagType("exception", DocTagType.getInstance().typed());
        setTagType("param", DocTagType.getInstance().keyed());
        setTagType("return", DocTagType.getInstance());
        setTagType("throws", DocTagType.getInstance().typed());
    }

}
