package net.bodz.bas.ar.zip.xf;

import java.io.IOException;

import net.bodz.bas.ar.zip.ExtraField;
import net.bodz.bas.ar.zip.ExtraFieldType;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;

/**
 * 4.5.8.3 Patch support is provided by PKPatchMaker(tm) technology and is covered under U.S.
 * Patents and Patents Pending. The use or implementation in a product of certain technological
 * aspects set forth in the current APPNOTE, including those with regard to strong encryption or
 * patching requires a license from PKWARE. Refer to the section in this document entitled
 * "Incorporating PKWARE Proprietary Technology into Your Product" for more information.
 */
@ExtraFieldType(id = 0x000f, sizeTotal = true)
public class XF_PatchDescriptor
        extends ExtraField {

    private static final long serialVersionUID = 1L;

    public static final int AUTO_DETECT = 1;
    public static final int SELF_PATCH = 2;
    public static final int ACTION = 0x0030;
    public static final int REACT_ABSENT = 0x0300;
    public static final int REACT_NEWER = 0x0c00;
    public static final int REACT_UNKNOWN = 0x3000;

    // Actions
    public static final int NONE = 0;
    public static final int ADD = 1;
    public static final int DEL = 2;
    public static final int PATCH = 3;

    // Reactions
    public static final int ASK = 0;
    public static final int SKIP = 1;
    public static final int IGNORE = 2;
    public static final int FAIL = 3;

    public short version;
    public int actions;
    public int oldSize;
    public int oldCRC;
    public int newSize;
    public int newCRC;

    @Override
    protected void _readObject(IDataIn in)
            throws IOException {
        version = in.readWord();
        actions = in.readDword();
        oldSize = in.readDword();
        oldCRC = in.readDword();
        newSize = in.readDword();
        newCRC = in.readDword();
    }

    @Override
    protected void _writeObject(IDataOut out)
            throws IOException {
        out.writeWord(version);
        out.writeDword(actions);
        out.writeDword(oldSize);
        out.writeDword(oldCRC);
        out.writeDword(newSize);
        out.writeDword(newCRC);
    }

}
