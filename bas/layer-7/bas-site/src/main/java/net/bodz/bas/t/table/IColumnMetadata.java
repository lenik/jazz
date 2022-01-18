package net.bodz.bas.t.table;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.xq.IElement;

public interface IColumnMetadata
        extends
            IJsonForm,
            IXmlForm {

    int getIndex();

    String getName();

    String getLabel();

    String getDescription();

    Class<?> getType();

    int getSqlType();

    boolean isPrimaryKey();

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
        out.entry("index", getIndex());
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
        out.attribute("index", getIndex());
        out.attribute("name", getName());

        String label = getLabel();
        if (label != null)
            out.attribute("label", getLabel());

        String description = getDescription();
        if (description != null)
            out.attribute("description", getDescription());

        out.attribute("type", getType().getName());
    }

}
