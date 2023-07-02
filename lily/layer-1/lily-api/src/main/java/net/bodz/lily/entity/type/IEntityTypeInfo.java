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

    @Deprecated
    Object parseId(String str)
            throws ParseException;

    @Deprecated
    default Object parseId(String str, Object fallback) {
        try {
            return parseId(str);
        } catch (ParseException e) {
            return fallback;
        }
    }

    /**
     * @throws IllegalArgumentException
     */
    Object newId(Object[] parameters)
            throws ReflectiveOperationException;

    default Object parseId(String[] columns)
            throws ParseException {
        Object[] fields = parseIdColumns(columns);
        try {
            return newId(fields);
        } catch (ReflectiveOperationException e) {
            throw new ParseException("error create id: " + e.getMessage(), e);
        }
    }

    default Object parseId(String[] columns, Object[] fallback) {
        try {
            return parseId(columns);
        } catch (ParseException e) {
            return fallback;
        }
    }

}
