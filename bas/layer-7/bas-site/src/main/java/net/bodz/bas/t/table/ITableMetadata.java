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

    String K_NAME = "name";
    String K_PRIMARY_KEY = "primary-key";

    String getName();

    String[] getPrimaryKey();

    IColumnMetadata[] getPrimaryKeyColumns();

    @Override
    default void writeObject(IJsonOut out)
            throws IOException, FormatException {
        out.entry(K_NAME, getName());
        String[] pk = getPrimaryKey();
        if (pk != null) {
            out.key(K_PRIMARY_KEY);
            out.array();
            for (String field : getPrimaryKey())
                out.value(field);
            out.endArray();
        }
        IRowSetMetadata.super.writeObject(out);
    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        out.attribute(K_NAME, getName());
        out.attribute(K_PRIMARY_KEY, StringArray.join(", ", getPrimaryKey()));
        IRowSetMetadata.super.writeObject(out);
    }

}
