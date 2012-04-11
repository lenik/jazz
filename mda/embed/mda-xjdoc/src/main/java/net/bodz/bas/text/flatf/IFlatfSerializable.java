package net.bodz.bas.text.flatf;

import java.io.IOException;
import java.text.ParseException;

public interface IFlatfSerializable {

    void writeObject(IFlatfOutput out)
            throws IOException;

    void loadObject(IFlatfLoader loader)
            throws IOException, ParseException;

}
