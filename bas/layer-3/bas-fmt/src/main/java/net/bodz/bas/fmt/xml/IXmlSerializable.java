package net.bodz.bas.fmt.xml;

import javax.xml.stream.XMLStreamException;

import org.w3c.dom.Element;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.source.SerializableForm;

@SerializableForm
public interface IXmlSerializable {

    default void writeObject(IXmlOutput out)
            throws XMLStreamException {
        XmlFn.defaultDump(this, out);
    }

    default void readObject(Element element)
            throws ParseException, LoaderException {
        IObjectXmlLoader loader = XmlFn.getDefaultLoader(this);
        loader.loadXmlToObject(this, element);
    }

    class xml
            extends XmlFn {
    }

}
