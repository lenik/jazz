package net.bodz.bas.t.catalog;

public interface IJDBCLoadSelector {

    default boolean selectSchema(SchemaOid id) {
        return true;
    }

    default boolean selectTable(TableOid oid, TableType type) {
        return true;
    }

//    default boolean selectTable(ITableMetadata table) {
//        return selectTable(table.getId());
//    }

    IJDBCLoadSelector ALL = new IJDBCLoadSelector() {
    };

    IJDBCLoadSelector NONE = new IJDBCLoadSelector() {
        @Override
        public boolean selectSchema(SchemaOid id) {
            return false;
        }

        @Override
        public boolean selectTable(TableOid oid, TableType type) {
            return false;
        }

    };

}
