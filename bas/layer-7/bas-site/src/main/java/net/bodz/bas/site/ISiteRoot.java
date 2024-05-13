package net.bodz.bas.site;

import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.rtx.IMutableAttributes;

public interface ISiteRoot
        extends
            IPathDispatchable,
            IMutableAttributes {

    String ATTRIBUTE_NAME = ISiteRoot.class.getName();

}
