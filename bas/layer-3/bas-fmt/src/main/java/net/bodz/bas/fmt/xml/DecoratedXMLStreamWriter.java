package net.bodz.bas.fmt.xml;

import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import net.bodz.bas.t.model.AbstractDecorator;

public class DecoratedXMLStreamWriter
        extends AbstractDecorator<XMLStreamWriter>
        implements
            XMLStreamWriter {

    private static final long serialVersionUID = 1L;

    public DecoratedXMLStreamWriter(XMLStreamWriter _orig) {
        super(_orig);
    }

    @Override
    public void writeStartElement(String localName)
            throws XMLStreamException {
        _orig.writeStartElement(localName);
    }

    @Override
    public void writeStartElement(String namespaceURI, String localName)
            throws XMLStreamException {
        _orig.writeStartElement(namespaceURI, localName);
    }

    @Override
    public void writeStartElement(String prefix, String localName, String namespaceURI)
            throws XMLStreamException {
        _orig.writeStartElement(prefix, localName, namespaceURI);
    }

    @Override
    public void writeEmptyElement(String namespaceURI, String localName)
            throws XMLStreamException {
        _orig.writeEmptyElement(namespaceURI, localName);
    }

    @Override
    public void writeEmptyElement(String prefix, String localName, String namespaceURI)
            throws XMLStreamException {
        _orig.writeEmptyElement(prefix, localName, namespaceURI);
    }

    @Override
    public void writeEmptyElement(String localName)
            throws XMLStreamException {
        _orig.writeEmptyElement(localName);
    }

    @Override
    public void writeEndElement()
            throws XMLStreamException {
        _orig.writeEndElement();
    }

    @Override
    public void writeEndDocument()
            throws XMLStreamException {
        _orig.writeEndDocument();
    }

    @Override
    public void close()
            throws XMLStreamException {
        _orig.close();
    }

    @Override
    public void flush()
            throws XMLStreamException {
        _orig.flush();
    }

    @Override
    public void writeAttribute(String localName, String value)
            throws XMLStreamException {
        _orig.writeAttribute(localName, value);
    }

    @Override
    public void writeAttribute(String prefix, String namespaceURI, String localName, String value)
            throws XMLStreamException {
        _orig.writeAttribute(prefix, namespaceURI, localName, value);
    }

    @Override
    public void writeAttribute(String namespaceURI, String localName, String value)
            throws XMLStreamException {
        _orig.writeAttribute(namespaceURI, localName, value);
    }

    @Override
    public void writeNamespace(String prefix, String namespaceURI)
            throws XMLStreamException {
        _orig.writeNamespace(prefix, namespaceURI);
    }

    @Override
    public void writeDefaultNamespace(String namespaceURI)
            throws XMLStreamException {
        _orig.writeDefaultNamespace(namespaceURI);
    }

    @Override
    public void writeComment(String data)
            throws XMLStreamException {
        _orig.writeComment(data);
    }

    @Override
    public void writeProcessingInstruction(String target)
            throws XMLStreamException {
        _orig.writeProcessingInstruction(target);
    }

    @Override
    public void writeProcessingInstruction(String target, String data)
            throws XMLStreamException {
        _orig.writeProcessingInstruction(target, data);
    }

    @Override
    public void writeCData(String data)
            throws XMLStreamException {
        _orig.writeCData(data);
    }

    @Override
    public void writeDTD(String dtd)
            throws XMLStreamException {
        _orig.writeDTD(dtd);
    }

    @Override
    public void writeEntityRef(String name)
            throws XMLStreamException {
        _orig.writeEntityRef(name);
    }

    @Override
    public void writeStartDocument()
            throws XMLStreamException {
        _orig.writeStartDocument();
    }

    @Override
    public void writeStartDocument(String version)
            throws XMLStreamException {
        _orig.writeStartDocument(version);
    }

    @Override
    public void writeStartDocument(String encoding, String version)
            throws XMLStreamException {
        _orig.writeStartDocument(encoding, version);
    }

    @Override
    public void writeCharacters(String text)
            throws XMLStreamException {
        _orig.writeCharacters(text);
    }

    @Override
    public void writeCharacters(char[] text, int start, int len)
            throws XMLStreamException {
        _orig.writeCharacters(text, start, len);
    }

    @Override
    public String getPrefix(String uri)
            throws XMLStreamException {
        return _orig.getPrefix(uri);
    }

    @Override
    public void setPrefix(String prefix, String uri)
            throws XMLStreamException {
        _orig.setPrefix(prefix, uri);
    }

    @Override
    public void setDefaultNamespace(String uri)
            throws XMLStreamException {
        _orig.setDefaultNamespace(uri);
    }

    @Override
    public void setNamespaceContext(NamespaceContext context)
            throws XMLStreamException {
        _orig.setNamespaceContext(context);
    }

    @Override
    public NamespaceContext getNamespaceContext() {
        return _orig.getNamespaceContext();
    }

    @Override
    public Object getProperty(String name)
            throws IllegalArgumentException {
        return _orig.getProperty(name);
    }

}
