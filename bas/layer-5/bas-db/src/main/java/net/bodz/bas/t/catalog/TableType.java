package net.bodz.bas.t.catalog;

import java.sql.ResultSet;
import java.sql.SQLException;

public enum TableType {

    UNKNOWN,

    VIEW,

    TABLE,
    SYSTEM_TABLE,

    TEMP,
    GLOBAL_TEMP,

    ALIAS,
    SYNONYM,

    SEQ,

    ;

    public boolean isTable() {
        switch (this) {
        case TABLE:
        case SYSTEM_TABLE:
            return true;
        case TEMP:
        case GLOBAL_TEMP:
            return true;
        default:
            return false;
        }
    }

    public boolean isView() {
        switch (this) {
        case VIEW:
            return true;
        default:
            return false;
        }
    }

    public static TableType parseJDBC(ResultSet rs)
            throws SQLException {
        return parseJDBC(rs, UNKNOWN);
    }

    public static TableType parseJDBC(ResultSet rs, TableType defaultType)
            throws SQLException {
        String jdbcVal = rs.getString("table_type");
        return parseJDBC(jdbcVal, defaultType);
    }

    public static TableType parseJDBC(String val) {
        return parseJDBC(val, UNKNOWN);
    }

    public static TableType parseJDBC(String val, TableType defaultType) {
        switch (val) {
        case "TABLE":
            return TABLE;

        case "SYSTEM TABLE":
            return SYSTEM_TABLE;

        case "VIEW":
            return VIEW;

        case "GLOBAL TEMPORARY":
            return GLOBAL_TEMP;

        case "LOCAL TEMPORARY":
            return TEMP;

        case "ALIAS":
            return ALIAS;

        case "SYNONYM":
            return SYNONYM;

        case "SEQ": // NOT USED.
            return SEQ;

        default:
            return defaultType;
        }
    }

}
