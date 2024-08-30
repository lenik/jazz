package net.bodz.bas.program.xjdoc;

import net.bodz.mda.xjdoc.taglib.AbstractTagLibrary;
import net.bodz.mda.xjdoc.tagtype.StringMapTag;
import net.bodz.mda.xjdoc.tagtype.StringTag;

public class CLITagLibrary
        extends AbstractTagLibrary {

    public CLITagLibrary() {
        // addTagType("option", new OptionTagType());
        addTagType("option", StringTag.class);
        addTagType("usage", StringMapTag.class);
    }

}
