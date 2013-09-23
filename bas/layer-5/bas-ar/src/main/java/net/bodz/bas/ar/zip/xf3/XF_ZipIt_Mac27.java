package net.bodz.bas.ar.zip.xf3;

import java.io.IOException;

import net.bodz.bas.ar.zip.ExtraField;
import net.bodz.bas.ar.zip.ExtraFieldType;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;

/**
 * The following is the layout of a shortened variant of the ZipIt extra block for Macintosh
 * (without "full name" entry). This variant is used by ZipIt 1.3.5 and newer for entries of files
 * (not directories) that do not have a MacBinary encoded file. The local-header and central-header
 * versions are identical.
 */
@ExtraFieldType(id = 0x2705, sizeTotal = true)
public class XF_ZipIt_Mac27
        extends ExtraField {

    private static final long serialVersionUID = 1L;

    /** big-endian "ZPIT" */
    public int sig;

    /** four-byte Mac file type string */
    public int fileType;
    /** four-byte Mac creator string */
    public int creator;

    /** big-endian: attributes from FInfo.frFlags, may be omitted */
    public short frFlags;
    /** 8reserved, may be omitted */
    public short reserved;

    @Override
    protected void _readObject(IDataIn in)
            throws IOException {
        sig = in.readDword();
        fileType = in.readDword();
        creator = in.readDword();
        frFlags = in.readWord();
        reserved = in.readWord();
    }

    @Override
    protected void _writeObject(IDataOut out)
            throws IOException {
        out.writeDword(sig);
        out.writeDword(fileType);
        out.writeDword(creator);
        out.writeWord(frFlags);
        out.writeWord(reserved);
    }

}
