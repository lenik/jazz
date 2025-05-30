package net.bodz.bas.t.catalog;

import java.io.IOException;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.potato.element.IProperty;

public interface IRowSetMetadata
        extends IJsonForm,
                IXmlForm {

    String K_COLUMNS = "columns";
    String K_COLUMN = "column";

    default ICatalogMetadata getCatalog() {
        ISchemaMetadata parent = getParent();
        if (parent == null)
            return null;
        else
            return parent.getParent();
    }

    ISchemaMetadata getParent();

    boolean isSparse();

    @NotNull
    List<? extends IColumnMetadata> getColumns();

    int getColumnCount();

    default IColumnMetadata getColumn(String name) {
        return getColumn(name, false);
    }

    IColumnMetadata getColumn(String name, boolean ignoreCase);

    default int indexOfColumn(String name, boolean ignoreCase) {
        if (name == null)
            throw new NullPointerException("name");
        int n = getColumnCount();
        for (int i = 0; i < n; i++) {
            IColumnMetadata column = getColumn(i);
            if (ignoreCase) {
                if (column.getName().equalsIgnoreCase(name))
                    return i;
            } else {
                if (column.getName().equals(name))
                    return i;
            }
        }
        return -1;
    }

    default int indexOfColumn(String name) {
        return indexOfColumn(name, false);
    }

    default int indexOfColumnIgnoreCase(String name) {
        return indexOfColumn(name, true);
    }

    /**
     * @param index 0-based.
     */
    IColumnMetadata getColumn(int index);

    default IColumnMetadata findColumnByJavaName(String javaName) {
        if (javaName == null)
            throw new NullPointerException("javaName");
        for (IColumnMetadata column : getColumns())
            if (Nullables.equals(column.getJavaName(), javaName))
                return column;
        return null;
    }

    default IProperty[] toPropertyArray() {
        List<? extends IColumnMetadata> columns = getColumns();
        int n = columns.size();
        IProperty[] properties = new IProperty[n];
        for (int i = 0; i < n; i++) {
            IColumnMetadata column = columns.get(i);
            IProperty property = column.getProperty();
            properties[i] = property;
        }
        return properties;
    }

    @Override
    default void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        out.key(K_COLUMNS);
        out.array();
        int cc = getColumnCount();
        for (int i = 0; i < cc; i++) {
            IColumnMetadata column = getColumn(i);
            out.object();
            column.jsonOut(out, opts);
            out.endObject();
        }
        out.endArray();
    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        out.beginElement(K_COLUMNS);
        int cc = getColumnCount();
        for (int i = 0; i < cc; i++) {
            IColumnMetadata column = getColumn(i);
            out.beginElement(K_COLUMN);
            column.writeObject(out);
            out.endElement();
        }
        out.endElement();
    }

}
