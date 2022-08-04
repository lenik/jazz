package net.bodz.bas.t.catalog;

public interface IJDBCLoadSelector {

    default boolean selectSchema(SchemaId id) {
        return true;
    }

    default boolean selectTable(TableId id) {
        return true;
    }

    IJDBCLoadSelector ALL = new IJDBCLoadSelector() {
    };

    IJDBCLoadSelector NONE = new IJDBCLoadSelector() {
        @Override
        public boolean selectSchema(SchemaId id) {
            return false;
        }

        @Override
        public boolean selectTable(TableId id) {
            return false;
        }
    };

}
