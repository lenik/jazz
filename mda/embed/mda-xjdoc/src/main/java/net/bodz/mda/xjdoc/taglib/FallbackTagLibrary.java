package net.bodz.mda.xjdoc.taglib;

import net.bodz.bas.meta.decl.Priority;
import net.bodz.mda.xjdoc.tagtype.StringTag;

public class FallbackTagLibrary
        extends AbstractTagLibrary {

    public FallbackTagLibrary() {
        this(Priority.VERYLOW);
    }

    public FallbackTagLibrary(int priority) {
        super(priority);
        setDefaultTagType(StringTag.class);
    }

}
