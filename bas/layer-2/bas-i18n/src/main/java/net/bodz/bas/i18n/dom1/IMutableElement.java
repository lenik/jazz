package net.bodz.bas.i18n.dom1;

import net.bodz.bas.i18n.dom.iString;

public interface IMutableElement
        extends IElement {

    void setName(String name);

    void setLabel(iString label);

    void setDescription(iString description);

    void setHelpDoc(iString helpDoc);

    void setDetailLevel(int verboseLevel);

    void setModifiers(int modifiers);

}
