package net.bodz.bas.i18n.dom1;

import net.bodz.bas.i18n.dom.DomainString;

public class DecoratedEditableElement
        extends DecoratedElement
        implements IEditableElement {

    private static final long serialVersionUID = 1L;

    public DecoratedEditableElement(IEditableElement _orig) {
        super(_orig);
    }

    @Override
    public IEditableElement getWrapped() {
        return (IEditableElement) super.getWrapped();
    }

    @Override
    public void setDisplayName(DomainString displayName) {
        getWrapped().setDisplayName(displayName);
    }

    @Override
    public void setDescription(DomainString description) {
        getWrapped().setDescription(description);
    }

    @Override
    public void setHelpDoc(DomainString helpDoc) {
        getWrapped().setHelpDoc(helpDoc);
    }

    @Override
    public void addTag(String tag) {
        getWrapped().addTag(tag);
    }

    @Override
    public void removeTag(String tag) {
        getWrapped().removeTag(tag);
    }

}
