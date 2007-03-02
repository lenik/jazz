package net.bodz.xml.xmod.modpdb;

public class PrimaryKey extends Index {

    public PrimaryKey(Table table, int... indexes) {
        super(table, indexes);
        unique = true;
    }

    public PrimaryKey(Table table, String... fieldNames) {
        super(table, fieldNames);
        unique = true;
    }

    public PrimaryKey(FieldsPartial base, int index1) {
        super(base, index1);
    }

    public PrimaryKey(FieldsPartial base, String name1) {
        super(base, name1);
    }

    @Override
    public void setUnique(boolean unique) {
        if (!unique)
            throw new IllegalArgumentException("primary-key must be unique");
        // this.unique = unique;
    }

}
