package net.bodz.bas.c.xml;

import java.io.IOException;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.IStreamOutputTarget;
import net.bodz.bas.rtx.INegotiation;

public interface IXMLForm<T> {

    int mdaFeaturesIndex = 260403962; // IXMLForm

    T parseXML(IStreamInputSource source, INegotiation negotiation)
            throws IOException, ParseException;

    void formatXML(T object, IStreamOutputTarget target, INegotiation negotiation)
            throws IOException;

}
