package net.bodz.lily.entity.type;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.repr.form.IFormDecl;
import net.bodz.lily.criteria.ICriteriaBuilder;

public interface IEntityTypeInfo {

    Class<?> getEntityClass();

    default Object newInstance() {
        Class<?> clazz = getEntityClass();
        Object instance;
        try {
            instance = clazz.getConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalUsageException("Error calling default constructor: " + e.getMessage(), e);
        }
        return instance;
    }

    IType getPotatoType();

    IFormDecl getFormDecl();

    Class<?> getIdClass();

    Class<?> getMapperClass();

    Class<?> getCriteriaBuilderClass();

    ICriteriaBuilder<?> newCriteriaBuilder();

    String[] getPrimaryKeyColumns();

    IProperty[] getPrimaryKeyProperties();

    String[] getPrimaryKeyPropertyNames();

    IProperty getPrimaryKeyProperty(String propertyName);

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

    Object parseSimpleId(String str)
            throws ParseException;

    default Object parseSimpleId(String str, Object fallback) {
        try {
            return parseSimpleId(str);
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

    IProperty getProperty(String propertyName);

    IProperty getPropertyForColumn(String columnName);

}
