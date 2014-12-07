package net.bodz.bas.i18n.dom1;

import net.bodz.bas.i18n.dom.iString;

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
    public void setLabel(iString label) {
        getWrapped().setLabel(label);
    }

    @Override
    public void setDescription(iString description) {
        getWrapped().setDescription(description);
    }

    @Override
    public void setHelpDoc(iString helpDoc) {
        getWrapped().setHelpDoc(helpDoc);
    }

    @Override
    public void setDetailLevel(int userLevel) {
        getWrapped().setDetailLevel(userLevel);
    }

    @Override
    public void setModifiers(int modifiers) {
        getWrapped().setModifiers(modifiers);
    }

    @Override
    public int getDetailLevel() {
        return getWrapped().getDetailLevel();
    }

    @Override
    public int getModifiers() {
        return getWrapped().getModifiers();
    }

}
