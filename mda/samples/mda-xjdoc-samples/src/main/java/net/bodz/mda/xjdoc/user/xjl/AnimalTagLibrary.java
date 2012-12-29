package net.bodz.mda.xjdoc.user.xjl;

import net.bodz.mda.xjdoc.tags.DocTagType;
import net.bodz.mda.xjdoc.tags.TagLibrary;

public class AnimalTagLibrary
        extends TagLibrary {

    public AnimalTagLibrary() {
        setTagType("color", DocTagType.getInstance());
    }

}
