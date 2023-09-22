package net.bodz.bas.doc.node;

import net.bodz.bas.doc.attr.SectionLevel;

public class DocSection
        extends DocParGroup {

    public static final String END_OF_SECTION = "\n";

    SectionLevel level;
    DocTextPar title = new DocTextPar(this);

    public DocSection(IDocNode parent, int level) {
        this(parent, SectionLevel.html(level));
    }

    public DocSection(IDocNode parent, SectionLevel level) {
        super(parent);
        if (level == null)
            throw new NullPointerException("level");
        this.level = level;
    }

    public DocSection level(SectionLevel level) {
        this.level = level;
        return this;
    }

    public SectionLevel getLevel() {
        return level;
    }

    public void setLevel(SectionLevel level) {
        if (level == null)
            throw new NullPointerException("level");
        this.level = level;
    }

    public DocTextPar getTitle() {
        return title;
    }

    public void setTitle(DocTextPar title) {
        this.title = title;
    }

    @Override
    public void buildText(StringBuilder a) {
        super.buildText(a);
        a.append(END_OF_SECTION);
    }

    @Override
    protected void nodeAccept(IDocVisitor visitor) {
        visitor.section(this);
    }

}
