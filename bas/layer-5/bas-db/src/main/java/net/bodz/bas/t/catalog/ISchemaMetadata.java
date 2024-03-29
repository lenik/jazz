package net.bodz.bas.t.catalog;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.t.tuple.QualifiedName;

public interface ISchemaMetadata
        extends
            IJavaQName,
            ITableDirectory,
            IViewDirectory,
            ICrossRefAnalyzer,
            IJsonForm,
            IXmlForm,
            IJDBCMetaDataSupport {

    String K_Q_NAME = "qName";
    String K_TABLES = "tables";
    String K_TABLE = "table";
    String K_VIEWS = "views";
    String K_VIEW = "view";

    SchemaOid getId();

    String getContextCatalogName();

    default String getName() {
        return getId().getSchemaName();
    }

    default String getCompactName() {
        return getCompactName(false);
    }

    default String getCompactName(boolean ignoreCase) {
        return getId().getCompactName(getContextCatalogName(), ignoreCase);
    }

    ICatalogMetadata getParent();

    String getCanonicalName(String name);

    Map<String, ITableMetadata> getTableMap();

    Collection<ITableMetadata> getTables();

    int getTableCount();

    ITableMetadata getTable(String name);

    Map<String, IViewMetadata> getViewMap();

    Collection<IViewMetadata> getViews();

    int getViewCount();

    IViewMetadata getView(String name);

    @Override
    default void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        getId().jsonOut(out, opts);

        QualifiedName qName = getJavaQName();
        if (qName != null)
            out.entry(K_Q_NAME, qName.getFullName());

        out.key(K_TABLES);
        out.object();
        for (String key : getTableMap().keySet()) {
            out.key(key);
            ITableMetadata table = getTable(key);
            if (table == null) {
                out.value(null);
                continue;
            }
            out.object();
            table.jsonOut(out, opts);
            out.endObject();
        }
        out.endObject();

        out.key(K_VIEWS);
        out.object();
        for (String key : getViewMap().keySet()) {
            out.key(key);
            IViewMetadata view = getView(key);
            if (view == null) {
                out.value(null);
                continue;
            }
            out.object();
            view.jsonOut(out, opts);
            out.endObject();
        }
        out.endObject();
    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        getId().writeObject(out);

        QualifiedName qName = getJavaQName();
        if (qName != null)
            out.attribute(K_Q_NAME, qName.getFullName());

        out.beginElement(K_TABLES);
        for (String key : getTableMap().keySet()) {
            ITableMetadata table = getTable(key);
            if (table == null)
                continue;
            out.beginElement(K_TABLE);
            table.writeObject(out);
            out.endElement();
        }
        out.endElement();

        out.beginElement(K_VIEWS);
        for (String key : getViewMap().keySet()) {
            IViewMetadata view = getView(key);
            if (view == null)
                continue;
            out.beginElement(K_VIEW);
            view.writeObject(out);
            out.endElement();
        }
        out.endElement();
    }

    default void accept(ICatalogVisitor visitor) {
        visitor.beginSchema(this);

        visitor.beginTables(this);
        for (ITableMetadata table : getTables())
            table.accept(visitor);
        visitor.endTables(this);

        visitor.beginViews(this);
        for (IViewMetadata view : getViews())
            view.accept(visitor);
        visitor.endViews(this);

        visitor.endSchema(this);
    }

}
