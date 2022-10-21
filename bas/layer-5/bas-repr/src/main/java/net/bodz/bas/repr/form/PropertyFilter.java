package net.bodz.bas.repr.form;

public interface PropertyFilter {

    boolean accept(IFormProperty fieldDecl);

    PropertyFilter TRUE = new TrueFieldDeclFilter();
}

class TrueFieldDeclFilter
        implements
            PropertyFilter {

    @Override
    public boolean accept(IFormProperty fieldDecl) {
        return true;
    }

}