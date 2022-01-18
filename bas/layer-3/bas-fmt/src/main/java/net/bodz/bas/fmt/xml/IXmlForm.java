package net.bodz.bas.fmt.xml;

import javax.xml.stream.XMLStreamException;

import org.w3c.dom.Element;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.fmt.xml.xq.QElement;
import net.bodz.bas.meta.source.SerializableForm;

@SerializableForm
public interface IXmlForm {

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
            throws XMLStreamException, FormatException {
        XmlFn.dump(this, out);
    }

    default void readObjectBoxed(IElement parent)
            throws ParseException, LoaderException {
        String tagName = getClass().getSimpleName();
        for (IElement child : parent.selectByTag(tagName)) {
            if (child.getParentNode() == parent) {
                readObject(child);
                break;
            }
        }
        throw new UnsupportedOperationException("Can't read from non-object json value.");
    }

    /**
     * @param out
     *            expects a value
     */
    default void writeObjectBoxed(IXmlOutput out)
            throws XMLStreamException, FormatException {
        String tagName = getClass().getSimpleName();
        out.beginElement(tagName);
        writeObject(out);
        out.endElement();
    }

    class xml
            extends XmlFn {
    }

    class dom
            extends DomFn {
    }

}
