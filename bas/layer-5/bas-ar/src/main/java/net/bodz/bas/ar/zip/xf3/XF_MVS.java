package net.bodz.bas.ar.zip.xf3;

import java.io.IOException;

import net.bodz.bas.ar.zip.ExtraField;
import net.bodz.bas.ar.zip.ExtraFieldType;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;

@ExtraFieldType(id = 0x470f)
public class XF_MVS
        extends ExtraField {

    private static final long serialVersionUID = 1L;

    @Override
    protected void _readObject(IDataIn in)
            throws IOException {
    }

    @Override
    protected void _writeObject(IDataOut out)
            throws IOException {
    }

}
