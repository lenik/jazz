package net.bodz.bas.doc.node;

import net.bodz.bas.doc.property.HorizAlignment;

public abstract class AbstractDocPar
        extends AbstractDocNode
        implements
            IPar {

    static final HorizAlignment DEFAULT_ALIGNMENT = HorizAlignment.LEFT;
    HorizAlignment alignment = DEFAULT_ALIGNMENT;

    public AbstractDocPar(INode parent) {
        super(parent);
    }

    @Override
    public boolean isPar() {
        return true;
    }

    @Override
    public int getItemIndex() {
        INode parent = getParent();
        if (parent.getType() == NodeType.LIST) {
            ListPar listPar = (ListPar) parent;
            // sync(pars) {...
            int n = listPar.pars.size();
            int itemIndex = 0;
            for (int i = 0; i < n; i++) {
                IPar par = listPar.pars.get(i);
                if (par == this)
                    return itemIndex;
                if (par.isListItem())
                    itemIndex++;
            }
            return -1;
        }
        return -1;
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

    @Override
    protected void nodeProperties(IDocVisitor visitor) {
        visitor.property("alignment", alignment);
    }

}
