package net.bodz.bas.ar.zip.xf3;

import java.io.IOException;

import net.bodz.bas.ar.zip.ExtraField;
import net.bodz.bas.ar.zip.ExtraFieldType;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;

/**
 * The following is the layout of a shortened variant of the ZipIt extra block for Macintosh used
 * only for directory entries. This variant is used by ZipIt 1.3.5 and newer to save some optional
 * Mac-specific information about directories. The local-header and central-header versions are
 * identical.
 */
@ExtraFieldType(id = 0x2805, sizeTotal = true)
public class XF_ZipIt_Mac28
        extends ExtraField {

    private static final long serialVersionUID = 1L;

    public static final int VIEW_EXPANDED = 1;

    /** big-endian "ZPIT" */
    public int sig;

    /**
     * big-endian
     * 
     * attributes from DInfo.frFlags, may be omitted
     */
    public short frFlags;

    /**
     * big-endian
     * 
     * ZipIt view flag, may be omitted
     * 
     * @see #VIEW_EXPANDED
     */
    public short view;

    @Override
    protected void _readObject(IDataIn in)
            throws IOException {
        sig = in.readDword();
        frFlags = in.readWord();
        view = in.readWord();
    }

    @Override
    protected void _writeObject(IDataOut out)
            throws IOException {
        out.writeDword(sig);
        out.writeWord(frFlags);
        out.writeWord(view);
    }

}
