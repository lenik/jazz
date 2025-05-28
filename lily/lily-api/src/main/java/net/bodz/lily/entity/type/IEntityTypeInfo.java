package net.bodz.lily.entity.type;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.repr.form.IFormDecl;
import net.bodz.bas.t.pojo.Pair;
import net.bodz.lily.criteria.ICriteriaBuilder;

public interface IEntityTypeInfo {

    Object newInstance();

    @NotNull
    Class<?> getEntityClass();

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
        return parseId2(columns).first;
    }

    default Pair<Object, Object[]> parseId2(String[] columns)
            throws ParseException {
        Object[] fields = parseIdColumns(columns);
        try {
            Object id = newId(fields);
            return Pair.of(id, fields);
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

    boolean isColumnPresent(String columnName);

}
