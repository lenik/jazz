package net.bodz.bas.t.catalog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.ReadOnlyException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.PropertyWriteException;

public interface IRowSet
        extends IJsonForm {

    String K_METADATA = "metadata";
    String K_ROWS = "rows";

    IRowSetMetadata getMetadata();

    Collection<? extends IRow> getRows();

    int getRowCount();

    IRow getRow(int index);

    // JSON FORM

    @Override
    default boolean wantObjectContext() {
        return false;
    }

    @Override
    default void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        throw new ReadOnlyException();
    }

    @Override
    default void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        throw new IllegalUsageException();
    }

    @Override
    default void jsonOutValue(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        IRowSetMetadata metadata = getMetadata();
        if (metadata == null)
            jsonOutArrays(out, opts);
        else
            jsonOutObjects(out, opts);
    }

    default void jsonOutArrays(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        out.array();
        for (IRow row : getRows())
            row.jsonOutArray(out, opts);
        out.endArray();
    }

    default void jsonOutObjects(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        IRowSetMetadata metadata = getMetadata();
        out.array();
        for (IRow row : getRows())
            row.jsonOutObject(out, opts, metadata);
        out.endArray();
    }

    // BEAN FORM

//    default <T> List<T> toBeans(@NotNull Supplier<T> beanFactory)
//            throws PropertyWriteException {
//        return toBeans(null, beanFactory);
//    }

    @NotNull
    default <T> List<T> toBeans(@NotNull Class<T> beanClass, @NotNull Supplier<T> beanFactory)
            throws PropertyWriteException {
        return toBeans(beanClass, beanFactory, null);
    }

    @NotNull
    default <T> List<T> toBeans(@NotNull Class<T> beanClass, @NotNull Supplier<T> beanFactory, Function<Exception, T> errorHandler)
            throws PropertyWriteException {
        IRowSetMetadata metadata = getMetadata();
        if (metadata == null)
            throw new IllegalUsageException("w/o metadata");

        IProperty[] propertyArray = getMetadata().toPropertyArray();
        for (IProperty property : propertyArray) {
            Class<?> declaringClass = property.getDeclaringClass();
            if (!declaringClass.isAssignableFrom(beanClass))
                throw new IllegalArgumentException("invalid bean class " + beanClass.getName());
        }

        int rowIndex = 0;
        List<T> beans = new ArrayList<>();
        for (IRow row : getRows()) {
            T bean = beanFactory.get();
            try {
                row.writeToBean(bean, propertyArray);
            } catch (PropertyWriteException e) {
                if (errorHandler == null)
                    throw new RuntimeException("Error converting row " + rowIndex + ": " + e.getMessage(), e);
                else {
                    T fallback = errorHandler.apply(e);
                    bean = fallback;
                }
            }
            beans.add(bean);
            rowIndex++;
        }
        return beans;
    }

}
