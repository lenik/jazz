package net.bodz.bas.program.xjdoc;

import net.bodz.mda.xjdoc.taglib.AbstractTagLibrary;
import net.bodz.mda.xjdoc.tagtype.StringMapTagType;

public class CLITagLibrary
        extends AbstractTagLibrary {

    public CLITagLibrary() {
        // addTagType("option", new OptionTagType());

        addTagType("usage", StringMapTagType.getInstance());
    }

}
