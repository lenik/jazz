package net.bodz.bas.doc.node;

public class FontStyleEnv
        extends DocRunGroup {

    boolean bold;
    boolean italic;
    boolean underline;
    boolean strikeline;

    public FontStyleEnv(IDocNode parent) {
        super(parent);
    }

    public boolean isBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public boolean isItalic() {
        return italic;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    public boolean isUnderline() {
        return underline;
    }

    public void setUnderline(boolean underline) {
        this.underline = underline;
    }

    public boolean isStrikeline() {
        return strikeline;
    }

    public void setStrikeline(boolean strikeline) {
        this.strikeline = strikeline;
    }

    @Override
    protected void nodeAccept(IDocVisitor visitor) {
        visitor.fontStyleEnv(this);
    }

}
