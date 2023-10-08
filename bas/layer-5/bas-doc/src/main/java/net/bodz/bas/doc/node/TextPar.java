package net.bodz.bas.doc.node;

import net.bodz.bas.doc.node.util.DocNodeList;
import net.bodz.bas.doc.property.MeasureLength;
import net.bodz.bas.t.list.IAutoList;

public class TextPar
        extends AbstractDocPar
        implements
            IHaveRuns {

    MeasureLength lineSpacing;
    MeasureLength indent;
    MeasureLength leadingIndent;

    public final DocNodeList<IRun> runs //
            = new DocNodeList<>(() -> new TextRun(this));

    public TextPar(INode parent) {
        super(parent);
    }

    @Override
    public NodeType getType() {
        return NodeType.TEXT_PAR;
    }

    @Override
    public DocNodeList<IRun> getRuns() {
        return runs;
    }

    @Override
    public IAutoList<IRun> getChildren() {
        return runs;
    }

    @Override
    public boolean canReduce() {
        if (lineSpacing != null || indent != null || leadingIndent != null)
            return false;
        for (IRun run : runs)
            if (!run.canReduce())
                return false;
        return true;
    }

    @Override
    public boolean isTextPar() {
        return true;
    }

    public boolean isSimpleText() {
        int n = runs.size();
        switch (n) {
        case 0:
            return true;
        case 1:
            IRun run = runs.get(0);
            if (run.isTextRun())
                // if (run.canReduce(nParents))
                return true;
        }
        return false;
    }

    @Override
    public void buildText(StringBuilder a) {
        for (IRun run : runs) {
            run.buildText(a);
        }
        a.append(END_OF_PAR);
    }

    @Override
    public void setText(String s) {
        runs.clear();
        addText(s);
    }

    public TextRun addText() {
        TextRun textRun = new TextRun(this);
        return runs.append(textRun);
    }

    public TextRun addText(String s) {
        TextRun textRun = new TextRun(this);
        textRun.setText(s);
        return runs.append(textRun);
    }

    @Override
    public void nodeAccept(IDocVisitor visitor) {
        visitor.textPar(this);
    }

    @Override
    public void internalAccept(IDocVisitor visitor) {
        for (IRun run : runs)
            run.accept(visitor);
    }

    public MeasureLength getLineSpacing() {
        return lineSpacing;
    }

    public void setLineSpacing(MeasureLength lineSpacing) {
        this.lineSpacing = lineSpacing;
    }

    public MeasureLength getIndent() {
        return indent;
    }

    public void setIndent(MeasureLength indent) {
        this.indent = indent;
    }

    public MeasureLength getLeadingIndent() {
        return leadingIndent;
    }

    public void setLeadingIndent(MeasureLength leadingIndent) {
        this.leadingIndent = leadingIndent;
    }

}
