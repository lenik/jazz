package net.bodz.bas.data.struct;

import net.bodz.bas.io.IDataIn;
import net.bodz.bas.text.rst.RstObject;

public abstract class RstDataStruct
        extends RstObject
        implements IDataStruct {

    private static final long serialVersionUID = 1L;

    @Override
    public int size() {
        return new ReflectSizeComputer(this).sizeof();
    }

    @Override
    public IDataIn transfer(int format) {
        return IDataStruct.fn.transfer(this, format);
    }

}
