package net.bodz.bas.t.table;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonSerializable;
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

    String name;
    String description;

    Class<?> type;
    boolean jsonType;
    boolean xmlType;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        jsonType = IJsonSerializable.class.isAssignableFrom(type);
        xmlType = IXmlSerializable.class.isAssignableFrom(type);
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
    public void readObject(JsonObject o)
            throws ParseException {
        name = o.getString("name");
        description = o.getString("description");

        String typeName = o.getString("type");
        setTypeByName(typeName);
    }

    @Override
    public void readObject(IElement o)
            throws ParseException, LoaderException {
        name = o.a("name").getString();
        description = o.a("description").getString();

        String typeName = o.a("type").getString();
        setTypeByName(typeName);
    }

    @Override
    public Object readJson(Object jsonBox)
            throws ParseException {
        if (jsonType) {
            IJsonSerializable obj;
            try {
                obj = (IJsonSerializable) type.newInstance();
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
            IJsonSerializable obj = (IJsonSerializable) value;
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

}
