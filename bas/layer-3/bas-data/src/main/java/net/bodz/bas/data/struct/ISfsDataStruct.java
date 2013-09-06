package net.bodz.bas.data.struct;

import java.io.IOException;

import net.bodz.bas.text.structf.IStructfHandler;
import net.bodz.bas.text.structf.IStructfOutput;

public interface ISfsDataStruct
        extends IDataStruct, IStructfHandler {

    void writeObject(IStructfOutput out)
            throws IOException;

}
