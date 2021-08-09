package net.bodz.bas.fmt.xml;

import javax.xml.stream.XMLStreamException;

import org.w3c.dom.Element;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.fmt.xml.xq.QElement;
import net.bodz.bas.meta.source.SerializableForm;

@SerializableForm
public interface IXmlSerializable {

    default void readObject(Element element)
            throws ParseException, LoaderException {
        QElement wrapped = QElement.wrap(element);
        readObject(wrapped);
    }

    default void readObject(IElement element)
            throws ParseException, LoaderException {
        IObjectXmlLoader loader = XmlFn.getDefaultLoader(this);
        loader.loadXmlToObject(this, element);
    }

    default void writeObject(IXmlOutput out)
            throws XMLStreamException {
        XmlFn.dump(this, out);
    }

    class xml
            extends XmlFn {
    }

    class dom
            extends DomFn {
    }

}
