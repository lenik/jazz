package net.bodz.bas.doc.node;

import net.bodz.bas.t.stack.NodePredicates;

public class FontStyleEnv
        extends AbstractRunGroup {

    boolean bold;
    boolean italic;
    boolean underline;
    boolean strikeline;

    public FontStyleEnv(INode parent) {
        super(parent);
    }

    @Override
    public NodeType getType() {
        return NodeType.FONT_STYLE_ENV;
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

    public boolean isEffectiveBold() {
        for (FontStyleEnv node : ancestors(NodePredicates.FONT_STYLE_ENV))
            if (node.isBold())
                return true;
        return false;
    }

    public boolean isEffectiveItalic() {
        for (FontStyleEnv node : ancestors(NodePredicates.FONT_STYLE_ENV))
            if (node.isItalic())
                return true;
        return false;
    }

    public boolean isEffectiveUnderline() {
        for (FontStyleEnv node : ancestors(NodePredicates.FONT_STYLE_ENV))
            if (node.isUnderline())
                return true;
        return false;
    }

    public boolean isEffectiveStrikeline() {
        for (FontStyleEnv node : ancestors(NodePredicates.FONT_STYLE_ENV))
            if (node.isStrikeline())
                return true;
        return false;
    }

    @Override
    public boolean canReduce() {
        if (bold || italic || underline || strikeline)
            return false;
        for (IRun run : runs)
            if (!run.canReduce())
                return false;
        return true;
    }

    @Override
    public void accept(IDocVisitor visitor) {
        visitor.fontStyleEnv(this);
    }

}
