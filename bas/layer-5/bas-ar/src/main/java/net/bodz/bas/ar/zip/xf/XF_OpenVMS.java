package net.bodz.bas.ar.zip.xf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.ar.zip.ExtraField;
import net.bodz.bas.ar.zip.ExtraFieldType;
import net.bodz.bas.err.BadFormatException;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;

/**
 * OpenVMS Extra Field Rules:
 * 
 * 4.5.6.1. There will be one or more attributes present, which will each be preceded by the above
 * TagX & SizeX values. These values are identical to the ATR$C_XXXX and ATR$S_XXXX constants which
 * are defined in ATR.H under OpenVMS C. Neither of these values will ever be zero.
 * 
 * 4.5.6.2. No word alignment or padding is performed.
 * 
 * 4.5.6.3. A well-behaved PKZIP/OpenVMS program should never produce more than one sub-block with
 * the same TagX value. Also, there will never be more than one "extra" block of type 0x000c in a
 * particular directory record.
 */
@ExtraFieldType(id = 0x000c, sizeTotal = true)
class XF_OpenVMS
        extends ExtraField {

    private static final long serialVersionUID = 1L;

    public static class Attr {
        public short tag;
        public short size;
        public byte[] data;
    }

    int crc;
    public final List<Attr> attrs = new ArrayList<Attr>();

    @Override
    protected void _readObject(IDataIn in)
            throws IOException {
        crc = in.readDword();

        attrs.clear();
        int remaining = size - 8;
        while (remaining > 0) {
            Attr attr = new Attr();
            attr.tag = in.readWord();
            attr.size = in.readWord();
            attr.data = new byte[attr.size];
            in.readBytes(attr.data);
            attrs.add(attr);
            remaining -= attr.size;
        }

        if (remaining != 0)
            throw new BadFormatException("OpenVMS attributes unaligned!");
    }

    @Override
    protected void _writeObject(IDataOut out)
            throws IOException {
        out.writeDword(crc);
        for (Attr attr : attrs) {
            out.writeWord(attr.tag);
            out.writeWord(attr.size);
            out.write(attr.data);
        }
    }

}
