package net.bodz.mda.xjdoc.flatf;

import java.io.IOException;
import java.text.ParseException;

public interface IFlatfSerializable {

    void readObject(IFlatfInput in)
            throws IOException, ParseException;

    void writeObject(IFlatfOutput out)
            throws IOException;

}
