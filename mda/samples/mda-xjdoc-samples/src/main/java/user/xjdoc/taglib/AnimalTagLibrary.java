package user.xjdoc.taglib;

import net.bodz.mda.xjdoc.taglib.AbstractTagLibrary;
import net.bodz.mda.xjdoc.tagtype.TextTag;

public class AnimalTagLibrary
        extends AbstractTagLibrary {

    public AnimalTagLibrary() {
        addTagType("color", TextTag.class);
    }

}
