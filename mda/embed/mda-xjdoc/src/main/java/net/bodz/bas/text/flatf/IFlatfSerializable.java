package net.bodz.bas.text.flatf;

import java.io.IOException;

import net.bodz.bas.rtx.IOptions;

public interface IFlatfSerializable {

    void writeObject(IFlatfOutput out, IOptions options)
            throws IOException;

    // void readObject(IFlatfInput in, INegotiation options)
    // throws IOException, ParseException;

    /**
     * @param sectionName
     *            <code>null</code> if it's outside of any section.
     */
    ISectionHandler getSectionHandler(String sectionName, IOptions options);

}
