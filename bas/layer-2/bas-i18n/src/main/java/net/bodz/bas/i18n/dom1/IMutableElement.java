package net.bodz.bas.i18n.dom1;

import java.io.Serializable;

import net.bodz.bas.i18n.dom.iString;

public interface IMutableElement
        extends IElement, Serializable {

    void setName(String name);

    void setLabel(iString label);

    void setDescription(iString description);

    void setHelpDoc(iString helpDoc);

    void setVerboseLevel(int verboseLevel);

    void setModifiers(int modifiers);

}
