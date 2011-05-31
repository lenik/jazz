package net.bodz.bas.xml;

import java.io.IOException;

import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.IStreamOutputTarget;
import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.NegotiationException;
import net.bodz.bas.util.exception.ParseException;

public interface IXMLForm<T> {

    int traitsIndex = 260403962; // IXMLForm

    T parseXML(IStreamInputSource source, INegotiation negotiation)
            throws IOException, ParseException, NegotiationException;

    void formatXML(T object, IStreamOutputTarget target, INegotiation negotiation)
            throws IOException;

}
