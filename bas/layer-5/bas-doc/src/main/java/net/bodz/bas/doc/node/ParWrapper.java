package net.bodz.bas.doc.node;

public abstract class ParWrapper
        extends AbstractDocPar {

    public IPar par;

    public ParWrapper(INode parent) {
        super(parent);
    }

    @Override
    public void buildText(StringBuilder a) {
        if (par != null)
            par.buildText(a);
    }

    @Override
    public synchronized void setText(String s) {
        par = new TextPar(this);
        par.setText(s);
    }

    @Override
    public void internalAccept(IDocVisitor visitor) {
        if (par != null)
            par.accept(visitor);
    }

    public Table addTable() {
        return addPar(new Table(this));
    }

    public Hr addHr() {
        return addPar(new Hr(this));
    }

    public TextPar addTextPar() {
        return addPar(new TextPar(this));
    }

    public <T extends IPar> T addPar(T par) {
        if (this.par != null)
            throw new IllegalStateException("already added.");
        this.par = par;
        return par;
    }

}
