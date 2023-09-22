package net.bodz.bas.doc.node;

import net.bodz.bas.doc.attr.MeasureLength;

public class FontEnv
        extends DocRunGroup {

    String family;
    MeasureLength size;
    MeasureLength charSpacing;

    public FontEnv(IDocNode parent) {
        super(parent);
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
    protected void nodeAccept(IDocVisitor visitor) {
        visitor.fontEnv(this);
    }

}
