package net.bodz.bas.fmt.xml;

import javax.xml.stream.XMLStreamException;

public interface IXmlOverrides {

    boolean writeSpecialXmlEntry(IXmlOutput out, String name)
            throws XMLStreamException;

}
