package net.bodz.bas.fmt.xml;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import net.bodz.bas.fmt.api.IDataCodec;
import net.bodz.bas.fmt.api.StdDataCodec;
import net.bodz.bas.t.set.FramedMarks;

public interface IXmlOutput
        extends
            XMLStreamWriter {

    IDataCodec codec = new StdDataCodec();

    FramedMarks getMarks();

    void element(String name, String text)
            throws XMLStreamException;

    void element(String name, Object text)
            throws XMLStreamException;

    void element(String name, IXmlSerializable child)
            throws XMLStreamException;

    void beginElement(String name)
            throws XMLStreamException;

    void endElement()
            throws XMLStreamException;

    void _attribute(String name, String data)
            throws XMLStreamException;

    void attribute(String name, int value)
            throws XMLStreamException;

    void attribute(String name, long value)
            throws XMLStreamException;

    void attributeHex(String name, byte value)
            throws XMLStreamException;

    void attributeHex(String name, short value)
            throws XMLStreamException;

    void attributeHex(String name, int value)
            throws XMLStreamException;

    void attributeHex(String name, long value)
            throws XMLStreamException;

    void attribute(String name, boolean value)
            throws XMLStreamException;

    void attribute(String name, float value)
            throws XMLStreamException;

    void attribute(String name, double value)
            throws XMLStreamException;

    void attribute(String name, char value)
            throws XMLStreamException;

    void attribute(String name, Enum<?> value)
            throws XMLStreamException;

    void attribute(String name, String value)
            throws XMLStreamException;

    void attribute(String name, byte[] buf)
            throws XMLStreamException;

    void attribute(String name, byte[] buf, int off, int len)
            throws XMLStreamException;

    void attribute(String name, short[] buf)
            throws XMLStreamException;

    void attribute(String name, short[] buf, int off, int len)
            throws XMLStreamException;

    void attribute(String name, int[] buf)
            throws XMLStreamException;

    void attribute(String name, int[] buf, int off, int len)
            throws XMLStreamException;

    void attribute(String name, long[] buf)
            throws XMLStreamException;

    void attribute(String name, long[] buf, int off, int len)
            throws XMLStreamException;

    void attribute(String name, float[] buf)
            throws XMLStreamException;

    void attribute(String name, float[] buf, int off, int len)
            throws XMLStreamException;

    void attribute(String name, double[] buf)
            throws XMLStreamException;

    void attribute(String name, double[] buf, int off, int len)
            throws XMLStreamException;

    void attribute(String name, boolean[] buf)
            throws XMLStreamException;

    void attribute(String name, boolean[] buf, int off, int len)
            throws XMLStreamException;

    void attribute(String name, char[] buf)
            throws XMLStreamException;

    void attribute(String name, char[] buf, int off, int len)
            throws XMLStreamException;

    void attribute(String name, String[] buf)
            throws XMLStreamException;

    void attribute(String name, String[] buf, int off, int len)
            throws XMLStreamException;

}
