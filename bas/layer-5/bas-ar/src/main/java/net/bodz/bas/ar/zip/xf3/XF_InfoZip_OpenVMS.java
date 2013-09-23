package net.bodz.bas.ar.zip.xf3;

import java.io.IOException;

import net.bodz.bas.ar.zip.ExtraField;
import net.bodz.bas.ar.zip.ExtraFieldType;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;

@ExtraFieldType(id = 0x4d49)
public class XF_InfoZip_OpenVMS
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
