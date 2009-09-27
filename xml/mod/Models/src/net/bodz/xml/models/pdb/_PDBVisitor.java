package net.bodz.xml.models.pdb;

import net.bodz.xml.models.pdb.PDB.Import;

public class _PDBVisitor implements PDBVisitor {

    @Override
    public boolean visit(Import import1) {
        return true;
    }

    @Override
    public boolean visit(PDB pdb) {
        return true;
    }

    @Override
    public boolean visit(Table table) {
        return true;
    }

    @Override
    public boolean visit(Field field) {
        return true;
    }

    @Override
    public boolean visit(Index index) {
        return true;
    }

    @Override
    public boolean visit(Check check) {
        return true;
    }

    @Override
    public boolean visit(Trigger trigger) {
        return true;
    }

    @Override
    public boolean visit(Instance instance) {
        return true;
    }

    @Override
    public boolean visit(View view) {
        return true;
    }

    @Override
    public boolean visit(View.Field field) {
        return true;
    }

}
