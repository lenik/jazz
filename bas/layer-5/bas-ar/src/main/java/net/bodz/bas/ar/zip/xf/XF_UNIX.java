package net.bodz.bas.ar.zip.xf;

import java.io.IOException;

import net.bodz.bas.ar.zip.ExtraField;
import net.bodz.bas.ar.zip.ExtraFieldType;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;

@ExtraFieldType(id = 0x000d)
class XF_UNIX
        extends ExtraField {

    private static final long serialVersionUID = 1L;

    public int atime;
    public int mtime;
    public short uid;
    public short gid;

    /**
     * The variable length data field will contain file type specific data. Currently the only
     * values allowed are the original "linked to" file names for hard or symbolic links, and the
     * major and minor device node numbers for character and block device nodes. Since device nodes
     * cannot be either symbolic or hard links, only one set of variable length data is stored. Link
     * files will have the name of the original file stored. This name is NOT NULL terminated. Its
     * size can be determined by checking TSize - 12. Device entries will have eight bytes stored as
     * two 4 byte entries (in little endian format). The first entry will be the major device
     * number, and the second the minor device number.
     */
    public byte[] data;

    @Override
    protected void _readObject(IDataIn in)
            throws IOException {
        atime = in.readDword();
        mtime = in.readDword();
        uid = in.readWord();
        gid = in.readWord();
        data = new byte[size - 12];
        in.readBytes(data);
    }

    @Override
    protected void _writeObject(IDataOut out)
            throws IOException {
        out.writeDword(atime);
        out.writeDword(mtime);
        out.writeWord(uid);
        out.writeWord(gid);
        out.write(data);
    }

}

/** Reserved for file stream and fork descriptors */
@ExtraFieldType(id = 0x000e)
abstract class XF_FileStreamFork
        extends ExtraField {

    private static final long serialVersionUID = 1L;

}
