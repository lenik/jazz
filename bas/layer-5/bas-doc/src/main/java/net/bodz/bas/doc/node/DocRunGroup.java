package net.bodz.bas.doc.node;

import net.bodz.bas.t.list.AutoList;
import net.bodz.bas.t.list.IAutoList;

public class DocRunGroup
        extends AbstractDocRun {

    public final IAutoList<IDocRun> runs //
            = new AutoList<>(() -> new DocTextRun(this));

    public DocRunGroup(IDocNode parent) {
        super(parent);
    }

    @Override
    public boolean haveRuns() {
        return true;
    }

    @Override
    public void buildText(StringBuilder a) {
        for (IDocRun run : runs)
            run.buildText(a);
    }

    @Override
    public void setText(String s) {
        runs.clear();
        addTextRun(s);
    }

    @Override
    public void internalAccept(IDocVisitor visitor) {
        for (IDocRun run : runs)
            run.accept(visitor);
    }

    public DocTextRun setTextRun(String s) {
        runs.clear();
        return addTextRun(s);
    }

    public DocTextRun addTextRun(String s) {
        return runs.append(new DocTextRun(this, s));
    }

    public DocRunGroup addEnv() {
        return runs.append(new DocRunGroup(this));
    }

    public FontEnv addFontEnv() {
        return runs.append(new FontEnv(this));
    }

    public FontStyleEnv addFontStyleEnv() {
        return runs.append(new FontStyleEnv(this));
    }

}
