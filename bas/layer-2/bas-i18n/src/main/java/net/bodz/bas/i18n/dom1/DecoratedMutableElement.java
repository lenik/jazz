package net.bodz.bas.i18n.dom1;

import net.bodz.bas.i18n.dom.DomainString;

public class DecoratedMutableElement
        extends DecoratedElement
        implements IMutableElement {

    private static final long serialVersionUID = 1L;

    public DecoratedMutableElement(IMutableElement _orig) {
        super(_orig);
    }

    @Override
    public IMutableElement getWrapped() {
        return (IMutableElement) super.getWrapped();
    }

    @Override
    public void setName(String name) {
        getWrapped().setName(name);
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
    public void setVerboseLevel(int userLevel) {
        getWrapped().setVerboseLevel(userLevel);
    }

    @Override
    public void setModifiers(int modifiers) {
        getWrapped().setModifiers(modifiers);
    }

    @Override
    public int getVerboseLevel() {
        return getWrapped().getVerboseLevel();
    }

    @Override
    public int getModifiers() {
        return getWrapped().getModifiers();
    }

}
