package user.xjdoc.taglib;

import net.bodz.mda.xjdoc.taglib.AbstractTagLibrary;
import net.bodz.mda.xjdoc.tagtype.TextTag;

public class AnimalTagLibrary
        extends AbstractTagLibrary {

    public AnimalTagLibrary() {
        this(0);
    }

    public AnimalTagLibrary(int priority) {
        super(priority);
        addTagType("color", TextTag.class);
    }

}
