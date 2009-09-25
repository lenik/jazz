package net.bodz.xml.models.pdb;

public class ClusteredIndex extends Index {

    public ClusteredIndex(FieldsPartial base, int index1) {
        super(base, index1);
    }

    public ClusteredIndex(FieldsPartial base, String name1) {
        super(base, name1);
    }

    public ClusteredIndex(Table table, int... indexes) {
        super(table, indexes);
    }

    public ClusteredIndex(Table table, String... names) {
        super(table, names);
    }

}
