package net.bodz.bas.ar.zip.xf;

import java.io.IOException;

import net.bodz.bas.ar.zip.ExtraField;
import net.bodz.bas.ar.zip.ExtraFieldType;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;

/** IBM S/390 (Z390), AS/400 (I400) attributes - uncompressed */
@ExtraFieldType(id = 0x0065)
class XF_MVS
        extends ExtraField {

    private static final long serialVersionUID = 1L;

    /** EBCDIC "Z390" 0xE9F3F9F0 or "T4MV" for TargetFour */
    public int id;
    public byte[] data;

    @Override
    protected void _readObject(IDataIn in)
            throws IOException {
        id = in.readDword();
        data = new byte[size - 4];
        in.readBytes(data);
    }

    @Override
    protected void _writeObject(IDataOut out)
            throws IOException {
        out.writeDword(id);
        out.write(data);
    }

}
