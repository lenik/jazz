package net.bodz.bas.doc.node;

import net.bodz.bas.t.list.AutoList;
import net.bodz.bas.t.list.IAutoList;

public class DocTextPar
        extends AbstractDocPar {

    double lineSpacing;
    double indent;
    double leadingIndent;

    public final IAutoList<IDocRun> runs //
            = new AutoList<>(() -> new DocTextRun(this));

    public DocTextPar(IDocNode parent) {
        super(parent);
    }

    @Override
    public IAutoList<IDocRun> getChildren() {
        return runs;
    }

    @Override
    public void buildText(StringBuilder a) {
        for (IDocRun run : runs) {
            run.buildText(a);
        }
        a.append(END_OF_PAR);
    }

    @Override
    public void setText(String s) {
        runs.clear();
        addText(s);
    }

    public DocTextRun addText() {
        DocTextRun textRun = new DocTextRun(this);
        return runs.append(textRun);
    }

    public DocTextRun addText(String s) {
        DocTextRun textRun = new DocTextRun(this);
        textRun.setText(s);
        return runs.append(textRun);
    }

    @Override
    public void nodeAccept(IDocVisitor visitor) {
        visitor.textPar(this);
    }

    @Override
    public void internalAccept(IDocVisitor visitor) {
        for (IDocRun run : runs)
            run.accept(visitor);
    }

}
