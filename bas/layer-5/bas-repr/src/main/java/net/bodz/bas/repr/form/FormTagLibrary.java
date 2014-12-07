package net.bodz.bas.repr.form;

import net.bodz.mda.xjdoc.taglib.AbstractTagLibrary;
import net.bodz.mda.xjdoc.tagtype.I18nStringTagType;

public class FormTagLibrary
        extends AbstractTagLibrary {

    public FormTagLibrary() {
        addTagType("placeholder", I18nStringTagType.getInstance());
    }

}
