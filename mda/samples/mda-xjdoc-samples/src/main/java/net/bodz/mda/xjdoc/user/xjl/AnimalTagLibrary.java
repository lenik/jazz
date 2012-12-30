package net.bodz.mda.xjdoc.user.xjl;

import net.bodz.mda.xjdoc.taglib.TagLibrary;
import net.bodz.mda.xjdoc.tagtype.iStringTagType;

public class AnimalTagLibrary
        extends TagLibrary {

    public AnimalTagLibrary() {
        setTagType("color", iStringTagType.getInstance());
    }

}
