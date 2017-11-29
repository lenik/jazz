package net.bodz.bas.t.variant.conv;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.bas.err.TypeConvertException;

public class SqlDateVarConverter
        extends AbstractVarConverter<java.sql.Date> {

    DateFormat dateFormat = Dates.YYYY_MM_DD;

    public SqlDateVarConverter() {
        super(java.sql.Date.class);
    }

    @Override
    public java.sql.Date fromString(String in)
            throws TypeConvertException {
        try {
            Date date = dateFormat.parse(in);
            return new java.sql.Date(date.getTime());
        } catch (ParseException e) {
            throw new TypeConvertException("Failed to parse date " + in, e);
        }
    }

    @Override
    public String toString(java.sql.Date value) {
        return dateFormat.format(value);
    }

    @Override
    public Number toNumber(java.sql.Date value) {
        return value.getTime();
    }

    @Override
    public boolean toBoolean(java.sql.Date value) {
        return true;
    }

    public static final SqlDateVarConverter instance = new SqlDateVarConverter();

}
