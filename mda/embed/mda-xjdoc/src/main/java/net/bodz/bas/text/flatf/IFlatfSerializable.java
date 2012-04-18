package net.bodz.bas.text.flatf;

import java.io.IOException;

import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.NegotiationException;

public interface IFlatfSerializable {

    void writeObject(IFlatfOutput out, INegotiation negotiation)
            throws IOException, NegotiationException;

    // void readObject(IFlatfInput in, INegotiation negotiation)
    // throws IOException, NegotiationException, ParseException;

    ISectionHandler getSectionHandler(String sectionName, INegotiation negotiation)
            throws NegotiationException;

}
