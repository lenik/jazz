package net.bodz.mda.xjdoc.user.xjl;

import net.bodz.mda.xjdoc.taglib.TagLibrary;
import net.bodz.mda.xjdoc.tagtype.I18nStringTagType;

public class AnimalTagLibrary
        extends TagLibrary {

    public AnimalTagLibrary() {
        addTagType("color", I18nStringTagType.getInstance());
    }

}
