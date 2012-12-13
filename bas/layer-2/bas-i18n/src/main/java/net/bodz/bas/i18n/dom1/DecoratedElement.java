package net.bodz.bas.i18n.dom1;

import java.util.Set;

import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.bas.t.model.AbstractDecorator;

public class DecoratedElement
        extends AbstractDecorator<IElement>
        implements IElement {

    private static final long serialVersionUID = 1L;

    public DecoratedElement(IElement _orig) {
        super(_orig);
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
    public int getUserLevel() {
        return getWrapped().getUserLevel();
    }

    @Override
    public int getModifiers() {
        return getWrapped().getModifiers();
    }

    @Override
    public Set<String> getTagNames() {
        return getWrapped().getTagNames();
    }

}
