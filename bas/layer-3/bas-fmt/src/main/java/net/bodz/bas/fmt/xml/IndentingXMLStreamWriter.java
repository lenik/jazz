package net.bodz.bas.fmt.xml;

import java.util.Stack;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class IndentingXMLStreamWriter
        extends DecoratedXMLStreamWriter {

    private static final long serialVersionUID = 1L;

    private static final int S_START = 0;
    private static final int S_ELEMENT = 1;
    private static final int S_DATA = 2;
    private int state = S_START;
    private Stack<Integer> stateStack = new Stack<>();
    private String indentStep = "  ";
    private int depth = 0;

    public IndentingXMLStreamWriter(XMLStreamWriter writer) {
        super(writer);
    }

    public int getIndentStep() {
        return indentStep.length();
    }

    public void setIndentStep(int indentStep) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < indentStep; i++)
            s.append(' ');
        this.setIndentStep(s.toString());
    }

    public void setIndentStep(String s) {
        this.indentStep = s;
    }

    private void onStartElement()
            throws XMLStreamException {
        stateStack.push(S_ELEMENT);
        state = S_START;
        if (depth > 0) {
            _orig.writeCharacters("\n");
        }
        this.doIndent();
        ++depth;
    }

    private void onEndElement()
            throws XMLStreamException {
        --depth;
        if (state == S_ELEMENT) {
            _orig.writeCharacters("\n");
            this.doIndent();
        }
        state = stateStack.pop();
    }

    private void onEmptyElement()
            throws XMLStreamException {
        state = S_ELEMENT;
        if (depth > 0) {
            _orig.writeCharacters("\n");
        }
        this.doIndent();
    }

    private void doIndent()
            throws XMLStreamException {
        if (depth > 0) {
            for (int i = 0; i < depth; ++i) {
                _orig.writeCharacters(this.indentStep);
            }
        }
    }

    @Override
    public void writeStartDocument()
            throws XMLStreamException {
        _orig.writeStartDocument();
        _orig.writeCharacters("\n");
    }

    @Override
    public void writeStartDocument(String version)
            throws XMLStreamException {
        _orig.writeStartDocument(version);
        _orig.writeCharacters("\n");
    }

    @Override
    public void writeStartDocument(String encoding, String version)
            throws XMLStreamException {
        _orig.writeStartDocument(encoding, version);
        _orig.writeCharacters("\n");
    }

    @Override
    public void writeStartElement(String localName)
            throws XMLStreamException {
        this.onStartElement();
        _orig.writeStartElement(localName);
    }

    @Override
    public void writeStartElement(String namespaceURI, String localName)
            throws XMLStreamException {
        this.onStartElement();
        _orig.writeStartElement(namespaceURI, localName);
    }

    @Override
    public void writeStartElement(String prefix, String localName, String namespaceURI)
            throws XMLStreamException {
        this.onStartElement();
        _orig.writeStartElement(prefix, localName, namespaceURI);
    }

    @Override
    public void writeEmptyElement(String namespaceURI, String localName)
            throws XMLStreamException {
        this.onEmptyElement();
        _orig.writeEmptyElement(namespaceURI, localName);
    }

    @Override
    public void writeEmptyElement(String prefix, String localName, String namespaceURI)
            throws XMLStreamException {
        this.onEmptyElement();
        _orig.writeEmptyElement(prefix, localName, namespaceURI);
    }

    @Override
    public void writeEmptyElement(String localName)
            throws XMLStreamException {
        this.onEmptyElement();
        _orig.writeEmptyElement(localName);
    }

    @Override
    public void writeEndElement()
            throws XMLStreamException {
        this.onEndElement();
        _orig.writeEndElement();
    }

    @Override
    public void writeCharacters(String text)
            throws XMLStreamException {
        state = S_DATA;
        _orig.writeCharacters(text);
    }

    @Override
    public void writeCharacters(char[] text, int start, int len)
            throws XMLStreamException {
        state = S_DATA;
        _orig.writeCharacters(text, start, len);
    }

    @Override
    public void writeCData(String data)
            throws XMLStreamException {
        state = S_DATA;
        _orig.writeCData(data);
    }

}