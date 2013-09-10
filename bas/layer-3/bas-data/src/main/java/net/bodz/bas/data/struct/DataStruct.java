package net.bodz.bas.data.struct;

import java.io.IOException;

import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;
import net.bodz.bas.text.rst.IElementHandler;
import net.bodz.bas.text.rst.IRstOutput;
import net.bodz.bas.text.rst.IRstSerializable;
import net.bodz.bas.text.rst.ReflectElementHandler;
import net.bodz.bas.text.rst.ReflectRstDumper;

public abstract class DataStruct
        implements IDataStruct, IRstSerializable {

    private static final long serialVersionUID = 1L;

    @Override
    public void readObject(IDataIn in)
            throws IOException {
    }

    @Override
    public void writeObject(IDataOut out)
            throws IOException {
    }

    @Override
    public void writeObject(IRstOutput out)
            throws IOException {
        ReflectRstDumper.dump(out, this);
    }

    @Override
    public IElementHandler getElementHandler() {
        return new ReflectElementHandler(this);
    }

}
