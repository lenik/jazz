package net.bodz.xml.models.pdb;

public class Index extends FieldsPartial {

    protected boolean unique;

    public Index(Table table, int... indexes) {
        super(table, indexes);
    }

    public Index(Table table, String... names) {
        super(table, names);
    }

    public Index(FieldsPartial base, int index1) {
        super(base, index1);
    }

    public Index(FieldsPartial base, String name1) {
        super(base, name1);
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

}
