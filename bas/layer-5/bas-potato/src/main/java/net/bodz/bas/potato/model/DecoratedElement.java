package net.bodz.bas.potato.model;

import java.util.Set;

import net.bodz.bas.i18n.dom.DomainString;

public class DecoratedElement
        extends DecoratedAnnotatedElement
        implements IElement {

    private static final long serialVersionUID = 1L;

    public DecoratedElement(IElement _orig) {
        super(_orig);
    }

    @Override
    public IElement getWrapped() {
        return (IElement) _orig; // super.getWrapped();
    }

    @Override
    public Class<?> getDeclaringClass() {
        return getWrapped().getDeclaringClass();
    }

    @Override
    public String getName() {
        return getWrapped().getName();
    }

    @Override
    public DomainString getDisplayName() {
        return getWrapped().getDisplayName();
    }

    @Override
    public DomainString getDescription() {
        return getWrapped().getDescription();
    }

    @Override
    public DomainString getHelpDoc() {
        return getWrapped().getHelpDoc();
    }

    @Override
    public int getPreferenceLevel() {
        return getWrapped().getPreferenceLevel();
    }

    @Override
    public int getModifiers() {
        return getWrapped().getModifiers();
    }

    @Override
    public Set<String> getTags() {
        return getWrapped().getTags();
    }

}
