package net.bodz.bas.data.struct;

import java.io.IOException;
import java.io.Serializable;

import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;

public interface IDataStruct
        extends Serializable {

    void readObject(IDataIn in)
            throws IOException;

    void writeObject(IDataOut out)
            throws IOException;

}
