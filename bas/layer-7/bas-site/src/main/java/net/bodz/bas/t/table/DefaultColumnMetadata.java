package net.bodz.bas.t.table;

import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.IXmlSerializable;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IParser;

public class DefaultColumnMetadata
        implements
            IColumnMetadata {

    int index;
    String name;
    String label;
    String description; // comment..

    Class<?> type;
    int sqlType;
    boolean jsonType;
    boolean xmlType;

    boolean primaryKey;

    @Override
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
        jsonType = IJsonForm.class.isAssignableFrom(type);
        xmlType = IXmlSerializable.class.isAssignableFrom(type);
    }

    @Override
    public int getSqlType() {
        return sqlType;
    }

    public void setSqlType(int sqlType) {
        this.sqlType = sqlType;
    }

    public void setTypeByName(String typeName)
            throws ParseException {
        Class<?> clazz;
        try {
            clazz = Class.forName(typeName);
        } catch (ClassNotFoundException e) {
            throw new ParseException(e.getMessage(), e);
        }
        setType(clazz);
    }

    @Override
    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Override
    public Object parse(String s)
            throws ParseException {
        IParser<?> parser = Typers.getTyper(type, IParser.class);
        return parser.parse(s);
    }

    @Override
    public Object readJson(Object jsonBox)
            throws ParseException {
        if (jsonType) {
            IJsonForm obj;
            try {
                obj = (IJsonForm) type.newInstance();
            } catch (Exception e) {
                throw new ParseException("Failed to instantiate " + type, e);
            }
            obj.readObjectBoxed(jsonBox);
            return obj;
        }

        IParser<?> parser = Typers.getTyper(type, IParser.class);
        if (parser != null) {
            String text = jsonBox.toString();
            Object value = parser.parse(text);
            return value;
        }

        throw new UnsupportedOperationException(//
                "Don't know how to convert json " + jsonBox + " to " + type);
    }

    @Override
    public void writeJson(IJsonOut out, Object value)
            throws IOException, FormatException {
        if (value == null) {
            out.value(null);
            return;
        }

        if (!type.isInstance(value))
            throw new IllegalArgumentException("Not an instance of " + type);

        if (jsonType) {
            IJsonForm obj = (IJsonForm) value;
            obj.writeObjectBoxed(out);
            return;
        }

        JsonFn.writeObject(out, value, null);
    }

    String getPreferredTagName() {
        String tagName = name; // type.getSimpleName();
        return tagName;
    }

    @Override
    public Object readXml(IElement enclosing)
            throws ParseException, LoaderException {
        if (xmlType) {
            IXmlSerializable obj;
            try {
                obj = (IXmlSerializable) type.newInstance();
            } catch (Exception e) {
                throw new ParseException("Failed to instantiate " + type, e);
            }
            obj.readObjectBoxed(enclosing);
            return obj;
        }

        String text = enclosing.getTextContent();
        IParser<?> parser = Typers.getTyper(type, IParser.class);
        if (parser != null) {
            Object value = parser.parse(text);
            return value;
        }

        throw new UnsupportedOperationException(//
                "Don't know how to convert xml text content [" + text + "] to " + type);
    }

    @Override
    public void writeXml(IXmlOutput out, Object value)
            throws XMLStreamException, FormatException {
        if (value != null) {
            if (!type.isInstance(value))
                throw new IllegalArgumentException("Not an instance of " + type);

            if (xmlType) {
                IXmlSerializable obj = (IXmlSerializable) value;
                obj.writeObjectBoxed(out);
                return;
            }
        }

        String tagName = getPreferredTagName();
        out.beginElement(tagName);
        String text = String.valueOf(value);
        out.writeCharacters(text);
        out.endElement();
    }

    public void readObject(ResultSetMetaData jdbcMetadata, int i)
            throws SQLException {
        String name = jdbcMetadata.getColumnName(i);
        String label = jdbcMetadata.getColumnLabel(i);

        String typeName = jdbcMetadata.getColumnClassName(i);
        // int width = jdbcMetadata.getColumnDisplaySize(i);
        int sqlType = jdbcMetadata.getColumnType(i);

        this.setIndex(i - 1);
        this.setName(name);
        if (!Nullables.equals(name, label))
            this.setLabel(label);

        try {
            this.setTypeByName(typeName);
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        this.setSqlType(sqlType);
    }

    /** ⇱ Implementation Of {@link IJsonForm}. */
    /* _____________________________ */static section.iface __JSON__;

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        index = o.getInt("index", -1);
        name = o.getString("name");
        label = o.getString("label");
        description = o.getString("description");

        String typeName = o.getString("type");
        setTypeByName(typeName);

        sqlType = o.getInt("sqlType", 0);
    }

    /** ⇱ Implementation Of {@link IXmlSerializable}. */
    /* _____________________________ */static section.iface __XML__;

    @Override
    public void readObject(IElement o)
            throws ParseException, LoaderException {
        index = o.a("index").getInt(-1);
        name = o.a("name").getString();
        label = o.a("label").getString();
        description = o.a("description").getString();

        String typeName = o.a("type").getString();
        setTypeByName(typeName);
        sqlType = o.a("sqlType").getInt(0);
    }

    @Override
    public String toString() {
        return name;
    }

}
