package net.bodz.bas.ar.zip.xf;

import java.io.IOException;

import net.bodz.bas.ar.zip.ExtraField;
import net.bodz.bas.ar.zip.ExtraFieldType;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;

/** Reserved for IBM S/390 (Z390), AS/400 (I400) attributes - compressed */
@ExtraFieldType(id = 0x0066)
public class XF_OS400
        extends ExtraField {

    private static final long serialVersionUID = 1L;

    /** EBCDIC "I400" 0xC9F4F0F0 or "T4MV" for TargetFour. */
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
