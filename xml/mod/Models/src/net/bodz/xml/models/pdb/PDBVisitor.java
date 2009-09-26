package net.bodz.xml.models.pdb;

public interface PDBVisitor {

    void visit(PDB pdb);

    void visit(PDB.Import _import);

    void visit(Table table);

    void visit(Field field);

    void visit(Index index);

    void visit(Check check);

    void visit(Trigger trigger);

    void visit(View view);

    void visit(View.Field viewField);

    void visit(Instance instance);

}
