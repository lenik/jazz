package net.bodz.bas.i18n.dom1;

import net.bodz.bas.i18n.dom.DomainString;

public interface IEditableElement
        extends IElement {

    void setDisplayName(DomainString displayName);

    void setDescription(DomainString description);

    void setHelpDoc(DomainString helpDoc);

    // void setPreferenceLevel(int preferenceLevel);

    // void setModifiers(int modifiers);

    void addTag(String tag);

    void removeTag(String tag);

}
