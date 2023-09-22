package net.bodz.bas.doc.node;

import net.bodz.bas.t.list.AutoList;
import net.bodz.bas.t.list.IAutoList;

public class DocParGroup
        extends AbstractDocPar {

    public final IAutoList<IDocPar> pars //
            = new AutoList<>(() -> new DocTextPar(this));

    public DocParGroup(IDocNode parent) {
        super(parent);
    }

    @Override
    public boolean havePars() {
        return true;
    }

    @Override
    public IAutoList<IDocPar> getChildren() {
        return pars;
    }

    @Override
    public void buildText(StringBuilder a) {
        for (IDocPar par : pars) {
            par.buildText(a);
        }
    }

    @Override
    public void setText(String s) {
        pars.clear();
        addTextPar().addText(s);
    }

    @Override
    public void internalAccept(IDocVisitor visitor) {
//        visitor.beginChildren();
        for (IDocPar par : pars)
            par.accept(visitor);
//        visitor.endChildren();
    }

    public DocTextPar addTextPar() {
        return pars.append(new DocTextPar(this));
    }

    public DocListPar addListPar() {
        return pars.append(new DocListPar(this));
    }

    public DocListPar addListPar(boolean ordered) {
        return pars.append(new DocListPar(this, ordered));
    }

    public DocTable addTable() {
        return pars.append(new DocTable(this));
    }

    public DocHr addHr() {
        return pars.append(new DocHr(this));
    }

}
