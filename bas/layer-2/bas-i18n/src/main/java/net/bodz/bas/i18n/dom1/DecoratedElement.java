package net.bodz.bas.i18n.dom1;

import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.t.model.AbstractDecorator;

public abstract class DecoratedElement
        extends AbstractDecorator<IElement>
        implements IElement {

    private static final long serialVersionUID = 1L;

    protected DecoratedElement(IElement _orig) {
        super(_orig);
    }

    @Override
    public String getName() {
        return getWrapped().getName();
    }

    @Override
    public iString getLabel() {
        return getWrapped().getLabel();
    }

    @Override
    public iString getDescription() {
        return getWrapped().getDescription();
    }

    @Override
    public iString getHelpDoc() {
        return getWrapped().getHelpDoc();
    }

    @Override
    public int getDetailLevel() {
        return getWrapped().getDetailLevel();
    }

    @Override
    public int getModifiers() {
        return getWrapped().getModifiers();
    }

    @Override
    public int getPriority() {
        return getWrapped().getPriority();
    }

}
