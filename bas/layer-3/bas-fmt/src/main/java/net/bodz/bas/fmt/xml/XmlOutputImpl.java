package net.bodz.bas.fmt.xml;

import java.io.OutputStream;
import java.io.Writer;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class XmlOutputImpl
        extends AbstractXmlOutput {

    private static final long serialVersionUID = 1L;

    private XmlOutputImpl(XMLStreamWriter out) {
        super(out);
    }

    static XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();

    public static IXmlOutput from(OutputStream outputStream, String encoding)
            throws XMLStreamException {
        XMLStreamWriter xsw = outputFactory.createXMLStreamWriter(outputStream, encoding);
        xsw = indent(xsw);
        return from(xsw);
    }

    public static IXmlOutput from(Writer writer)
            throws XMLStreamException {
        XMLStreamWriter xsw = outputFactory.createXMLStreamWriter(writer);
        xsw = indent(xsw);
        return from(xsw);
    }

    public static IXmlOutput from(Appendable out)
            throws XMLStreamException {
        AppendableWriter aw = new AppendableWriter(out);
        return from(aw);
    }

    static XMLStreamWriter indent(XMLStreamWriter out) {
        return new IndentingXMLStreamWriter(out);
    }

    public static IXmlOutput from(XMLStreamWriter out) {
        if (out instanceof IXmlOutput)
            return (IXmlOutput) out;
        else
            return new XmlOutputImpl(out);
    }

    @Override
    public void beginElement(String name)
            throws XMLStreamException {
        _orig.writeStartElement(name);
    }

    @Override
    public void endElement()
            throws XMLStreamException {
        _orig.writeEndElement();
    }

    @Override
    public void _attribute(String name, String data)
            throws XMLStreamException {
        _orig.writeAttribute(name, data);
    }

    @Override
    public void flush()
            throws XMLStreamException {
        _orig.flush();
    }

}
