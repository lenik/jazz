package net.bodz.bas.doc.node;

import net.bodz.bas.doc.node.util.DocNodeList;
import net.bodz.bas.doc.property.PartLevel;

public class PartGroup
        extends AbstractDocPar {

    PartLevel level;
    public final DocNodeList<Part> parts = new DocNodeList<>(() -> new Part(this));

    public PartGroup(INode parent, int level) {
        this(parent, PartLevel.html(level));
    }

    public PartGroup(INode parent, PartLevel level) {
        super(parent);
        if (level == null)
            throw new NullPointerException("level");
        this.level = level;
    }

    @Override
    public NodeType getType() {
        return NodeType.PART_GROUP;
    }

    public PartGroup level(PartLevel level) {
        this.level = level;
        return this;
    }

    public PartLevel getLevel() {
        return level;
    }

    public void setLevel(PartLevel level) {
        if (level == null)
            throw new NullPointerException("level");
        this.level = level;
    }

    @Override
    public void buildText(StringBuilder a) {
        for (Part section : parts)
            section.buildText(a);
    }

    @Override
    public void setText(String s) {
        parts.clearExcept(0, true).setText(s);
    }

    @Override
    public void accept(IDocVisitor visitor) {
        visitor.partGroup(this);
    }

    public Part addPart(String title) {
        Part part = new Part(this);
        if (title != null)
            part.title.setText(title);
        return parts.append(part);
    }

}
