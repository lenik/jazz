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
 * The following is the layout of the NTFS attributes "extra" block. (Note: At this time the Mtime,
 * Atime and Ctime values MAY be used on any WIN32 system.)
 */
@ExtraFieldType(id = 0x000a, sizeTotal = true)
public class XF_NTFS
        extends ExtraField {

    private static final long serialVersionUID = 1L;

    public static class Attr {
        public short tag;
        public short size;
        public byte[] data;
    }

    /** Reserved for future use */
    public int reserved1;
    public final List<Attr> attrs = new ArrayList<Attr>();

    @Override
    protected void _readObject(IDataIn in)
            throws IOException {
        reserved1 = in.readDword();

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
            throw new BadFormatException("NTFS attributes unaligned!");
    }

    @Override
    protected void _writeObject(IDataOut out)
            throws IOException {
        out.writeDword(reserved1);
        for (Attr attr : attrs) {
            out.writeWord(attr.tag);
            out.writeWord(attr.size);
            out.write(attr.data);
        }
    }

}
