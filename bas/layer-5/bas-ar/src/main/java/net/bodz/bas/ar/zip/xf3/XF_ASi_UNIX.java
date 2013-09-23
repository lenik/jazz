package net.bodz.bas.ar.zip.xf3;

import java.io.IOException;

import net.bodz.bas.ar.zip.ExtraField;
import net.bodz.bas.ar.zip.ExtraFieldType;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;
import net.bodz.bas.io.data.DataInImplBE;

/**
 * Adds Unix file permission and UID/GID fields as well as symbolic link handling.
 * 
 * @see ftp://ftp.uu.net/pub/archiving/zip/doc/
 */
@ExtraFieldType(id = 0x756e, sizeTotal = true)
public class XF_ASi_UNIX
        extends ExtraField {

    private static final long serialVersionUID = 1L;

    /** CRC-32 of the remaining data */
    public int crc32;

    /** file permissions */
    public short mode;

    /**
     * symlink'd size OR major/minor dev num .
     * 
     * device numbers are currently not supported
     */
    public int sizDev;

    public short uid;
    public short gid;

    /** symbolic link filename */
    public byte[] targetRaw;

    @Override
    protected void _readObject(IDataIn in)
            throws IOException {
        DataInImplBE be = DataInImplBE.from(in);
        crc32 = be.readDword();
        mode = be.readWord();
        sizDev = be.readDword();
        uid = be.readWord();
        gid = be.readWord();

        boolean symlink = true;
        if (symlink) {
            targetRaw = new byte[sizDev];
            in.readBytes(targetRaw);
        }
    }

    @Override
    protected void _writeObject(IDataOut out)
            throws IOException {
        out.writeDword(crc32);
        out.writeWord(mode);
        out.writeDword(sizDev);
        out.writeWord(uid);
        out.writeWord(gid);

        boolean symlink = true;
        if (symlink)
            out.write(targetRaw);
    }

}
