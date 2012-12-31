package user.xjdoc.taglib;

import net.bodz.mda.xjdoc.taglib.AbstractTagLibrary;
import net.bodz.mda.xjdoc.tagtype.I18nStringTagType;

public class AnimalTagLibrary
        extends AbstractTagLibrary {

    public AnimalTagLibrary() {
        addTagType("color", I18nStringTagType.getInstance());
    }

}
