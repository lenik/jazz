package net.bodz.xml.models.pdb;

import net.bodz.xml.models.pdb.PDB.Import;

/**
 * Second Pass analyzation visitor.
 */
public class ReverseVisitor extends _PDBVisitor {

    /**
     * ignore imports, because they should be already imported by the first pass collect-visitor,
     * and all imported Tables/Views are merged into the singleton PDB instance, and they are going
     * to be visited in following steps.
     */
    @Override
    public boolean visit(Import _import) {
        return true;
    }

    @Override
    public boolean visit(Table table) {
        for (Index ref : table.getReferences()) {
            String refTableName = ref.getRef();
            Table refTable = null;
            // refTable.addReverseReference(table);
            ref.refTable = refTable;
        }
        return true;
    }

}
