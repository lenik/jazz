package net.bodz.bas.program.xjdoc;

import net.bodz.mda.xjdoc.taglib.AbstractTagLibrary;
import net.bodz.mda.xjdoc.tagtype.StringMapTagType;
import net.bodz.mda.xjdoc.tagtype.StringTagType;

public class CLITagLibrary
        extends AbstractTagLibrary {

    public CLITagLibrary() {
        // addTagType("option", new OptionTagType());
        addTagType("option", StringTagType.getInstance());
        addTagType("usage", StringMapTagType.getInstance());
    }

}
