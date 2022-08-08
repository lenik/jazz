package net.bodz.bas.t.catalog;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.xml.IXmlOutput;

public interface ITableMetadata
        extends
            ITableViewMetadata {

    String K_PRIMARY_KEY = "primaryKey";
    String K_FOREIGN_KEYS = "foreignKeys";
    String K_CROSS_REF = "reference";

    Map<String, CrossReference> getForeignKeys();

    CrossReference getForeignKey(String constraintName);

    default Set<TableId> getParentTableNames() {
        Set<TableId> parents = new LinkedHashSet<>();
        for (CrossReference ref : getForeignKeys().values()) {
            TableId parent = ref.getParentKey().getId();
            parents.add(parent);
        }
        return parents;
    }

    @Override
    default void writeObject(IJsonOut out)
            throws IOException, FormatException {
        ITableViewMetadata.super.writeObject(out);

        TableKey primaryKey = getPrimaryKey();
        if (primaryKey != null) {
            out.key(K_PRIMARY_KEY);
            primaryKey.writeObject(out);
        }

        Map<String, CrossReference> foreignKeys = getForeignKeys();
        if (!foreignKeys.isEmpty()) {
            out.key(K_FOREIGN_KEYS);
            out.array();
            for (CrossReference ref : foreignKeys.values())
                ref.writeObjectBoxed(out);
            out.endArray();
        }
    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        ITableViewMetadata.super.writeObject(out);

        TableKey primaryKey = getPrimaryKey();
        if (primaryKey != null) {
            out.beginElement(K_PRIMARY_KEY);
            primaryKey.writeObject(out);
            out.endElement();
        }

        Map<String, CrossReference> foreignKeys = getForeignKeys();
        if (!foreignKeys.isEmpty()) {
            out.beginElement(K_FOREIGN_KEYS);
            for (CrossReference ref : foreignKeys.values()) {
                out.beginElement(K_CROSS_REF);
                ref.writeObject(out);
                out.endElement();
            }
            out.endElement();
        }
    }

}
