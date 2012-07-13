package net.bodz.mda.xjdoc.user.xjl;

import net.bodz.mda.xjdoc.tags.DocTagType;
import net.bodz.mda.xjdoc.tags.TagBook;

public class AnimalTagBook
        extends TagBook {

    public AnimalTagBook() {
        setTagType("color", DocTagType.getInstance());
    }

}
