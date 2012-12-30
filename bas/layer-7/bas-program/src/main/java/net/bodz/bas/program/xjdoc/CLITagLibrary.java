package net.bodz.bas.program.xjdoc;

import net.bodz.mda.xjdoc.taglib.AbstractTagLibrary;
import net.bodz.mda.xjdoc.tagtype.I18nStringTagType;
import net.bodz.mda.xjdoc.tagtype.TagSpecKeyTagType;

public class CLITagLibrary
        extends AbstractTagLibrary {

    public CLITagLibrary() {
        // addTagType("option", new OptionTagType());

        addTagType("usage", new TagSpecKeyTagType( //
                I18nStringTagType.getInstance()));
    }

}
