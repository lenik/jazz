package net.bodz.bas.t.table;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.xml.IXmlOutput;

public interface ITableMetadata
        extends
            IRowSetMetadata {

    String K_PRIMARY_KEY = "primary-key";

    QualifiedTableName getQName();

    QualifiedTableName getDefaultName();

    default String getName() {
        return getQName().getTableName();
    }

    default String getCompactName() {
        return getCompactName(false);
    }

    default String getCompactName(boolean ignoreCase) {
        return getQName().getCompactName(getDefaultName());
    }

    @Override
    ISchemaMetadata getParent();

    String[] getPrimaryKey();

    IColumnMetadata[] getPrimaryKeyColumns();

    @Override
    default void writeObject(IJsonOut out)
            throws IOException, FormatException {
        getQName().writeObject(out);

        IRowSetMetadata.super.writeObject(out);

        String[] pk = getPrimaryKey();
        if (pk != null) {
            out.key(K_PRIMARY_KEY);
            out.array();
            for (String field : getPrimaryKey())
                out.value(field);
            out.endArray();
        }
    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        getQName().writeObject(out);

        IRowSetMetadata.super.writeObject(out);

        out.attribute(K_PRIMARY_KEY, StringArray.join(", ", getPrimaryKey()));
    }

    default void accept(IVisitor visitor) {
        visitor.beginTable(this);

        visitor.beginRowSet(this);
        int n = getColumnCount();
        for (int i = 0; i < n; i++)
            getColumn(i).accept(visitor);
        visitor.endRowSet(this);

        visitor.endTable(this);
    }

}
