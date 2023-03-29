package net.bodz.bas.data.struct;

import java.io.IOException;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;

public interface IOctetStreamForm {

    void readObject(IDataIn in)
            throws IOException, ParseException;

    void writeObject(IDataOut out)
            throws IOException;

}
