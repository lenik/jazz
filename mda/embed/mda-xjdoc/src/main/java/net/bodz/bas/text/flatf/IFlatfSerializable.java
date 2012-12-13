package net.bodz.bas.text.flatf;

import java.io.IOException;

import net.bodz.bas.rtx.INegotiation;

public interface IFlatfSerializable {

    void writeObject(IFlatfOutput out, INegotiation negotiation)
            throws IOException;

    // void readObject(IFlatfInput in, INegotiation negotiation)
    // throws IOException, ParseException;

    ISectionHandler getSectionHandler(String sectionName, INegotiation negotiation);

}
