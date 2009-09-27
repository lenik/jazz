package net.bodz.xml.models.pdb;

public interface PDBVisitor {

    boolean visit(PDB pdb);

    boolean visit(PDB.Import _import);

    boolean visit(Table table);

    boolean visit(Field field);

    boolean visit(Index index);

    boolean visit(Check check);

    boolean visit(Trigger trigger);

    boolean visit(View view);

    boolean visit(View.Field viewField);

    boolean visit(Instance instance);

}
