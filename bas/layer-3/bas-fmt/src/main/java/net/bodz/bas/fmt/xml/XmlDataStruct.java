package net.bodz.bas.fmt.xml;

import net.bodz.bas.data.struct.IDataStruct;
import net.bodz.bas.data.struct.ReflectSizeComputer;
import net.bodz.bas.io.IDataIn;

public abstract class XmlDataStruct
        extends XmlObject
        implements IDataStruct {

    private static final long serialVersionUID = 1L;

    @Override
    public int dataSize() {
        return new ReflectSizeComputer(this).sizeof();
    }

    @Override
    public IDataIn transfer(int format) {
        return IDataStruct.fn.transfer(this, format);
    }

}
