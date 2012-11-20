package net.bodz.bas.i18n.dom1;

import java.io.Serializable;

import net.bodz.bas.i18n.dom.DomainString;

public interface IEditableElement
        extends IElement, Serializable {

    void setDisplayName(DomainString displayName);

    void setDescription(DomainString description);

    void setHelpDoc(DomainString helpDoc);

    void setUserLevel(int userLevel);

    void setModifiers(int modifiers);

    void addTag(String tag);

    void removeTag(String tag);

}
