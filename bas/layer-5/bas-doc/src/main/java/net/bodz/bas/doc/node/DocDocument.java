package net.bodz.bas.doc.node;

import net.bodz.bas.doc.attr.SectionLevel;
import net.bodz.bas.t.list.AutoList;
import net.bodz.bas.t.list.IAutoList;

public class DocDocument
        extends AbstractDocNode {

    IDocPar title = new DocTextPar(this);

    public final IAutoList<DocSection> sections //
            = new AutoList<>(() -> new DocSection(this, SectionLevel.CHAPTER));

    public DocDocument() {
        super(null);
    }

    @Override
    public boolean isDocument() {
        return true;
    }

    @Override
    public DocDocument getDocument() {
        return this;
    }

    public IDocPar getTitle() {
        return title;
    }

    public void setTitle(IDocPar title) {
        this.title = title;
    }

    @Override
    public IAutoList<? extends DocSection> getChildren() {
        return sections;
    }

    @Override
    public void buildText(StringBuilder a) {
        for (DocSection section : sections) {
            section.buildText(a);
        }
    }

    @Override
    public void setText(String s) {
        sections.clear();
        DocSection section = new DocSection(this, SectionLevel.CHAPTER);
        section.setText(s);
        sections.add(section);
    }

    @Override
    protected void nodeAccept(IDocVisitor visitor) {
        visitor.document(this);
    }

    @Override
    public void internalAccept(IDocVisitor visitor) {
        for (DocSection section : sections)
            section.accept(visitor);
    }

}
