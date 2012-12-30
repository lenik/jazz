package net.bodz.bas.i18n.dom1;

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
    public DomainString getLabel() {
        return getWrapped().getLabel();
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
    public int getVerboseLevel() {
        return getWrapped().getVerboseLevel();
    }

    @Override
    public int getModifiers() {
        return getWrapped().getModifiers();
    }

}
