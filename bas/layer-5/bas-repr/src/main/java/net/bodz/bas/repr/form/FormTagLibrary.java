package net.bodz.bas.repr.form;

import net.bodz.mda.xjdoc.taglib.AbstractTagLibrary;
import net.bodz.mda.xjdoc.tagtype.TextTag;

public class FormTagLibrary
        extends AbstractTagLibrary {

    public FormTagLibrary() {
        this(0);
    }

    public FormTagLibrary(int priority) {
        super(priority);
        addTagType("placeholder", TextTag.class);
    }

}
