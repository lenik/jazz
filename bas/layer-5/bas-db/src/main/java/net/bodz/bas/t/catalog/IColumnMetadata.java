package net.bodz.bas.t.catalog;

import java.io.IOException;
import java.sql.ResultSetMetaData;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.NoSuchKeyException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.xq.IElement;

public interface IColumnMetadata
        extends
            IJsonForm,
            IXmlForm {

    IRowSetMetadata getParent();

    default int position() {
        int pos = getPositionOpt();
        if (pos == -1)
            throw new NoSuchKeyException(getName());
        return pos;
    }

    default int getPositionOpt() {
        IRowSetMetadata parent = getParent();
        if (parent == null)
            throw new IllegalStateException("Parent wasn't set.");
        return parent.indexOfColumn(getName());
    }

    String getName();

    String getLabel();

    String getDescription();

    Class<?> getType();

    int getSqlType();

    boolean isPrimaryKey();

    boolean isAutoIncrement();

    boolean isCaseSensitive();

    boolean isSearchable();

    boolean isCurrency();

    boolean isUnique();

    /**
     * @see ResultSetMetaData#columnNoNulls
     * @see ResultSetMetaData#columnNullable
     * @see ResultSetMetaData#columnNullableUnknown
     */
    int getNullableStatus();

    boolean isNullable();

    boolean isNullable(boolean fallback);

    boolean isSigned();

    boolean isReadOnly();

    boolean isWritable();

    boolean isDefinitelyWritable();

    int getColumnDisplaySize();

    int getPrecision();

    int getScale();

    Object parse(String s)
            throws ParseException;

    Object readJson(Object jsonValue)
            throws ParseException;

    void writeJson(IJsonOut out, Object value)
            throws IOException, FormatException;

    Object readXml(IElement enclosing)
            throws ParseException, LoaderException;

    void writeXml(IXmlOutput out, Object value)
            throws XMLStreamException, FormatException;

    @Override
    default void writeObject(IJsonOut out)
            throws IOException, FormatException {
//        out.entry("index", getIndex());
        out.entry("name", getName());

        String label = getLabel();
        if (label != null)
            out.entry("label", label);

        String description = getDescription();
        if (description != null)
            out.entry("description", getDescription());

        out.entry("type", getType().getName());
    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
//        out.attribute("index", getIndex());
        out.attribute("name", getName());

        String label = getLabel();
        if (label != null)
            out.attribute("label", getLabel());

        String description = getDescription();
        if (description != null)
            out.attribute("description", getDescription());

        out.attribute("type", getType().getName());
    }

    default void accept(ICatalogVisitor visitor) {
        visitor.column(this);
    }

}