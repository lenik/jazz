package net.bodz.bas.t.catalog;

public interface IJDBCLoadSelector {

    default SelectMode selectSchema(SchemaOid id) {
        return SelectMode.INCLUDE;
    }

    default SelectMode selectTable(TableOid oid, TableType type) {
        return SelectMode.SKIP;
    }

//    default boolean selectTable(ITableMetadata table) {
//        return selectTable(table.getId());
//    }

    IJDBCLoadSelector ALL = new IJDBCLoadSelector() {
    };

    IJDBCLoadSelector EXCLUDE = new IJDBCLoadSelector() {
        @Override
        public SelectMode selectSchema(SchemaOid id) {
            return SelectMode.EXCLUDE;
        }

        @Override
        public SelectMode selectTable(TableOid oid, TableType type) {
            return SelectMode.EXCLUDE;
        }

    };

    IJDBCLoadSelector SKIP = new IJDBCLoadSelector() {
        @Override
        public SelectMode selectSchema(SchemaOid id) {
            return SelectMode.SKIP;
        }

        @Override
        public SelectMode selectTable(TableOid oid, TableType type) {
            return SelectMode.SKIP;
        }

    };

}
