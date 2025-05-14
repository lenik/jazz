package net.bodz.bas.t.catalog;

import java.sql.ResultSetMetaData;

public enum NullableType {

    NULLABLE(ResultSetMetaData.columnNullable),

    NO_NULLS(ResultSetMetaData.columnNoNulls),

    UNKNOWN(ResultSetMetaData.columnNullableUnknown),

    ;

    final int intValue;

    NullableType(int intValue) {
        this.intValue = intValue;
    }

    public int intValue() {
        return intValue;
    }

    public static NullableType ofIntValue(int intValue) {
        switch (intValue) {
            case ResultSetMetaData.columnNullable:
                return NULLABLE;
            case ResultSetMetaData.columnNoNulls:
                return NO_NULLS;
        }
        return UNKNOWN;
    }

}
