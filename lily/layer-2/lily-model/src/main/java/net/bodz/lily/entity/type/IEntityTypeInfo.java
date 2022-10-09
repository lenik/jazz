package net.bodz.lily.entity.type;

import net.bodz.bas.err.ParseException;

public interface IEntityTypeInfo {

    Class<?> getEntityClass();

    Class<?> getIdClass();

    Class<?> getMapperClass();

    Class<?> getCrtieriaClass();

    int getIdColumnCount();

    Object[] parseIdColumns(String... columns)
            throws ParseException;

    default Object[] parseIdColumns(String[] columns, Object[] fallback) {
        try {
            return parseIdColumns(columns);
        } catch (ParseException e) {
            return fallback;
        }
    }

    Object parseId(String str)
            throws ParseException;

    default Object parseId(String str, Object fallback) {
        try {
            return parseId(str);
        } catch (ParseException e) {
            return fallback;
        }
    }

}
