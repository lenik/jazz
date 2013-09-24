package net.bodz.bas.ar.zip.xf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.ar.zip.ExtraField;
import net.bodz.bas.ar.zip.ExtraFieldType;
import net.bodz.bas.err.BadFormatException;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;

/** Record Management Controls */
@ExtraFieldType(id = 0x0018, sizeTotal = true)
public class XF_RecordMgmtCtrl
        extends ExtraField {

    private static final long serialVersionUID = 1L;

    public static class Attr {
        public short tag;
        public short size;
        public byte[] data;
    }

    public final List<Attr> attrs = new ArrayList<Attr>();

    @Override
    protected void _readObject(IDataIn in)
            throws IOException {
        attrs.clear();
        int remaining = size - 4;
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
        for (Attr attr : attrs) {
            out.writeWord(attr.tag);
            out.writeWord(attr.size);
            out.write(attr.data);
        }
    }

}
