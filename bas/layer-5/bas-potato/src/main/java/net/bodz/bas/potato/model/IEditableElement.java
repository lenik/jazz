package net.bodz.bas.potato.model;

import net.bodz.bas.i18n.dom.DomainString;

public interface IEditableElement
        extends IElement {

    void setDisplayName(DomainString displayName);

    void setDescription(DomainString description);

    void setHelpDoc(DomainString helpDoc);

}
