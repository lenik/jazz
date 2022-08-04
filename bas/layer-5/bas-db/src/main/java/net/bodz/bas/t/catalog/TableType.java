package net.bodz.bas.t.catalog;

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
