package net.bodz.bas.doc.node;

import net.bodz.bas.doc.property.PartLevel;

public class Part
        extends AbstractParGroup {

    public static final String END_OF_PART = "\n";

    public final TextPar title = new TextPar(this);

    public Part(PartGroup parent) {
        super(parent);
    }

    @Override
    public NodeType getType() {
        return NodeType.PART;
    }

    @Override
    public PartGroup getParent() {
        return (PartGroup) super.getParent();
    }

    public final PartLevel getLevel() {
        return getParent().getLevel();
    }

    public int getPartIndex() {
        return getChildIndex();
    }

    @Override
    public boolean canReduce() {
        return pars.isEmpty();
    }

    @Override
    public void buildText(StringBuilder a) {
        super.buildText(a);
        a.append(END_OF_PART);
    }

    @Override
    public void accept(IDocVisitor visitor) {
        int sectionIndex = getPartIndex();
        visitor.part(this, sectionIndex);
    }

}
