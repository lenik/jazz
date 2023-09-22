package net.bodz.bas.doc.node;

import net.bodz.bas.doc.attr.HorizAlignment;

public abstract class AbstractDocPar
        extends AbstractDocNode
        implements
            IDocPar {

    HorizAlignment alignment = HorizAlignment.LEFT;

    public AbstractDocPar(IDocNode parent) {
        super(parent);
    }

    @Override
    public boolean isPar() {
        return true;
    }

    @Override
    public HorizAlignment getAlignment() {
        return alignment;
    }

    public void setAlignment(HorizAlignment alignment) {
        if (alignment == null)
            throw new NullPointerException("alignment");
        this.alignment = alignment;
    }

}
