package net.bodz.bas.data.struct;

import net.bodz.bas.io.IDataIn;

public abstract class DataStruct
        implements IDataStruct {

    private static final long serialVersionUID = 1L;

    @Override
    public int dataSize() {
        return new ReflectSizeComputer(this).sizeof();
    }

    @Override
    public IDataIn transfer(int format) {
        return fn.transfer(this, format);
    }

}
