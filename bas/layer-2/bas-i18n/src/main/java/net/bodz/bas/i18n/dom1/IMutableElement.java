package net.bodz.bas.i18n.dom1;

import java.io.Serializable;

import net.bodz.bas.i18n.dom.DomainString;

public interface IMutableElement
        extends IElement, Serializable {

    void setName(String name);

    void setDisplayName(DomainString displayName);

    void setDescription(DomainString description);

    void setHelpDoc(DomainString helpDoc);

    void setVerboseLevel(int verboseLevel);

    void setModifiers(int modifiers);

}
