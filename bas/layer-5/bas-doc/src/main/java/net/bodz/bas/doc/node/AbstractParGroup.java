package net.bodz.bas.doc.node;

import net.bodz.bas.t.list.AutoList;
import net.bodz.bas.t.list.IAutoList;

public abstract class AbstractParGroup
        extends AbstractDocPar
        implements
            IHavePars {

    public final IAutoList<IPar> pars //
            = new AutoList<>(() -> new TextPar(this));

    public AbstractParGroup(INode parent) {
        super(parent);
    }

    @Override
    public IAutoList<IPar> getChildren() {
        return pars;
    }

    @Override
    public IAutoList<IPar> getPars() {
        return pars;
    }

    @Override
    public void buildText(StringBuilder a) {
        for (IPar par : pars) {
            par.buildText(a);
        }
    }

    @Override
    public void setText(String s) {
        pars.clear();
        addTextPar().addText(s);
    }

}
