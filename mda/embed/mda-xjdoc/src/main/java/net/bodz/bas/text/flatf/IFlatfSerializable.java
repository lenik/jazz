package net.bodz.bas.text.flatf;

import java.io.IOException;

import javax.free.INegotiation;
import javax.free.NegotiationException;

public interface IFlatfSerializable {

    void writeObject(IFlatfOutput out, INegotiation negotiation)
            throws IOException, NegotiationException;

    // void readObject(IFlatfInput in, INegotiation negotiation)
    // throws IOException, NegotiationException, ParseException;

    ISectionHandler getSectionHandler(String sectionName, INegotiation negotiation)
            throws NegotiationException;

}
