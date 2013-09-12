package net.bodz.bas.data.struct;

import java.io.IOException;
import java.io.Serializable;

import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;

public interface IDataStruct
        extends Serializable {

    int size();

    void readObject(IDataIn in)
            throws IOException;

    void writeObject(IDataOut out)
            throws IOException;

    int FORMAT_LE = 0;
    int FORMAT_BE = 1;

    IDataIn transfer(int format);

}
