package net.bodz.mda.xjdoc.model.javadoc;

import net.bodz.mda.xjdoc.taglib.TagLibrary;
import net.bodz.mda.xjdoc.tagtype.iStringTagType;

public class JavadocTagLibrary
        extends TagLibrary {

    {
        setTagType("author", iStringTagType.getInstance().repeat());
        setTagType("exception", iStringTagType.getInstance().typed());
        setTagType("param", iStringTagType.getInstance().keyed());
        setTagType("return", iStringTagType.getInstance());
        setTagType("throws", iStringTagType.getInstance().typed());
    }

}
