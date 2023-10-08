package net.bodz.bas.doc.node;

import net.bodz.bas.doc.node.util.DocNodeList;
import net.bodz.bas.doc.node.util.IDocNodeList;

public abstract class AbstractRunGroup
        extends AbstractDocRun
        implements
            IHaveRuns {

    public final DocNodeList<IRun> runs //
            = new DocNodeList<>(() -> new TextRun(this));

    public AbstractRunGroup(INode parent) {
        super(parent);
    }

    @Override
    public DocNodeList<IRun> getRuns() {
        return runs;
    }

    @Override
    public IDocNodeList<? extends INode> getChildren() {
        return runs;
    }

    @Override
    public void internalAccept(IDocVisitor visitor) {
        for (IRun run : runs)
            run.accept(visitor);
    }

}
