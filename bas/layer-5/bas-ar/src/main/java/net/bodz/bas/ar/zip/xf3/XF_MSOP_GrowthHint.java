package net.bodz.bas.ar.zip.xf3;

import java.io.IOException;

import net.bodz.bas.ar.zip.ExtraField;
import net.bodz.bas.ar.zip.ExtraFieldType;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;

/**
 * Microsoft Open Packaging Growth Hint
 */
@ExtraFieldType(id = (short) 0xa220)
public class XF_MSOP_GrowthHint
        extends ExtraField {

    private static final long serialVersionUID = 1L;

    public short sig;
    public short padVal;
    public byte[] padding;

    @Override
    protected void _readObject(IDataIn in)
            throws IOException {
        sig = in.readWord();
        padVal = in.readWord();
        padding = new byte[size];
        in.readBytes(padding);
    }

    @Override
    protected void _writeObject(IDataOut out)
            throws IOException {
        out.writeWord(sig);
        out.writeWord(padVal);
        out.write(padding);
    }

}
