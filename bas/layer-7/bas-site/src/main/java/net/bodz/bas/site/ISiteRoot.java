package net.bodz.bas.site;

import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.rtx.IAttributed;

public interface ISiteRoot
        extends
            IPathDispatchable,
            IAttributed {

    String ATTRIBUTE_NAME = ISiteRoot.class.getName();

}
