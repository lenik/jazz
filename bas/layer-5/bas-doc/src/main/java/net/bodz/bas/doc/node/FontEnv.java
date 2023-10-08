package net.bodz.bas.doc.node;

import net.bodz.bas.doc.property.MeasureLength;

public class FontEnv
        extends AbstractRunGroup {

    String family;
    MeasureLength size;
    MeasureLength charSpacing;

    public FontEnv(INode parent) {
        super(parent);
    }

    @Override
    public NodeType getType() {
        return NodeType.FONT_ENV;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public MeasureLength getSize() {
        return size;
    }

    public void setSize(MeasureLength size) {
        this.size = size;
    }

    public MeasureLength getCharSpacing() {
        return charSpacing;
    }

    public void setCharSpacing(MeasureLength charSpacing) {
        this.charSpacing = charSpacing;
    }

    @Override
    public boolean canReduce() {
        return family == null && size == null && charSpacing == null;
    }

    @Override
    protected void nodeAccept(IDocVisitor visitor) {
        visitor.fontEnv(this);
    }

}
