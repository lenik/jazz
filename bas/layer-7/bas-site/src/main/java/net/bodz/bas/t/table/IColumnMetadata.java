package net.bodz.bas.t.table;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.IXmlSerializable;
import net.bodz.bas.fmt.xml.xq.IElement;

public interface IColumnMetadata
        extends
            IJsonSerializable,
            IXmlSerializable {

    String getName();

    String getLabel();

    String getDescription();

    Class<?> getType();

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
        out.entry("name", getName());
        out.entry("label", getLabel());
        out.entry("description", getDescription());
        out.entry("type", getType().getName());
    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        out.attribute("name", getName());
        out.attribute("label", getLabel());
        out.attribute("description", getDescription());
        out.attribute("type", getType().getName());
    }

}
