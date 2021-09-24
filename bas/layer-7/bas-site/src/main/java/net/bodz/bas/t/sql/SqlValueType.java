package net.bodz.bas.t.sql;

import net.bodz.bas.db.sql.dialect.ISqlFormat;

public enum SqlValueType {

    DEFAULT,

    STRING,

    DATE,

    NAME,

    RAW,

    ;

    public String toSqlCode(ISqlFormat format, Object value) {
        return toSqlCode(format, value, null);
    }

    public String toSqlCode(ISqlFormat format, Object value, String nullStr) {
        if (value == null)
            return nullStr;
        switch (this) {
        case DEFAULT:
            return value.toString();

        case STRING:
            return format.qString(value.toString());

        case DATE:
            return format.qDate(value);

        case NAME:
            String name = (String) value;
            return format.qName(name);

        case RAW:
            String code = (String) value;
            return code;

        default:
            throw new IllegalArgumentException("unknown value type: " + this);
        }
    }

}
