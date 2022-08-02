package net.bodz.bas.t.table;

import java.io.IOException;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;

public interface ISchemaMetadata
        extends
            Iterable<ITableMetadata>,
            IJsonForm,
            IXmlForm {

    String K_TABLES = "tables";
    String K_TABLE = "table";

    QualifiedSchemaName getQName();

    QualifiedSchemaName getDefaultName();

    default String getName() {
        return getQName().getSchemaName();
    }

    default String getCompactName() {
        return getCompactName(false);
    }

    default String getCompactName(boolean ignoreCase) {
        return getQName().getCompactName(getDefaultName(), ignoreCase);
    }

    ICatalogMetadata getParent();

    /**
     * key: canonical name
     */
    Map<String, ITableMetadata> getTables();

    int getTableCount();

    ITableMetadata getTable(String name);

    default ITableMetadata findTable(String name, boolean ignoreCase) {
        if (ignoreCase)
            name = getCanonicalName(name);
        return getTable(name);
    }

    String getCanonicalName(String name);

    @Override
    default void writeObject(IJsonOut out)
            throws IOException, FormatException {
        getQName().writeObject(out);

        out.key(K_TABLES);
        out.object();
        for (String key : getTables().keySet()) {
            out.key(key);
            ITableMetadata table = getTable(key);
            if (table == null) {
                out.value(null);
                continue;
            }
            out.object();
            table.writeObject(out);
            out.endObject();
        }
        out.endObject();
    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        getQName().writeObject(out);

        out.beginElement(K_TABLES);
        for (String key : getTables().keySet()) {
            ITableMetadata table = getTable(key);
            if (table == null)
                continue;
            out.beginElement(K_TABLE);
            table.writeObject(out);
            out.endElement();
        }
        out.endElement();
    }

}
