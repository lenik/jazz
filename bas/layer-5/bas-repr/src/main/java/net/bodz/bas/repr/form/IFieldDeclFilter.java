package net.bodz.bas.repr.form;

public interface IFieldDeclFilter {

    boolean accept(IFieldDecl fieldDecl);

    IFieldDeclFilter TRUE = new TrueFieldDeclFilter();
}

class TrueFieldDeclFilter
        implements IFieldDeclFilter {

    @Override
    public boolean accept(IFieldDecl fieldDecl) {
        return true;
    }

}