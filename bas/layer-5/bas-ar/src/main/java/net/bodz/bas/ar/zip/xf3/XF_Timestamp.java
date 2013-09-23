package net.bodz.bas.ar.zip.xf3;

import java.io.IOException;

import net.bodz.bas.ar.zip.ExtraField;
import net.bodz.bas.ar.zip.ExtraFieldType;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;

/**
 * An extra field that stores additional file and directory timestamp data for zip entries. Each zip
 * entry can include up to three timestamps (modify, access, create*). The timestamps are stored as
 * 32 bit unsigned integers representing seconds since UNIX epoch (Jan 1st, 1970, UTC). This field
 * improves on zip's default timestamp granularity, since it allows one to store additional
 * timestamps, and, in addition, the timestamps are stored using per-second granularity (zip's
 * default behaviour can only store timestamps to the nearest <em>even</em> second).
 * <p/>
 * </p>
 * <p>
 * Unfortunately, 32 (unsigned) bits can only store dates up to the year 2106, and so this extra
 * field will eventually be obsolete. Enjoy it while it lasts!
 * </p>
 * <ul>
 * <li><b>modifyTime:</b> most recent time of file/directory modification (or file/dir creation if
 * the entry has not been modified since it was created).</li>
 * <li><b>accessTime:</b> most recent time file/directory was opened (e.g., read from disk). Many
 * people disable their operating systems from updating this value using the NOATIME mount option to
 * optimize disk behaviour, and thus it's not always reliable. In those cases it's always equal to
 * modifyTime.</li>
 * <li><b>*createTime:</b> modern linux file systems (e.g., ext2 and newer) do not appear to store a
 * value like this, and so it's usually omitted altogether in the zip extra field. Perhaps other
 * unix systems track this.</li>
 * </ul>
 * <p>
 * We're using the field definition given in Info-Zip's source archive:
 * zip-3.0.tar.gz/proginfo/extrafld.txt
 * </p>
 */
@ExtraFieldType(id = 0x5455 /* UT */, sizeTotal = true)
public class XF_Timestamp
        extends ExtraField {

    private static final long serialVersionUID = 1L;

    public static final byte MTIME_PRESENT = 1 << 0;
    public static final byte ATIME_PRESENT = 1 << 1;
    public static final byte CTIME_PRESENT = 1 << 2;

    public byte flags;

    /** time of last modification */
    public int mtime;

    /** time of last access */
    public int atime;

    /** time of original creation */
    public int ctime;

    @Override
    protected void _readObject(IDataIn in)
            throws IOException {
        flags = in.readByte();

        if ((flags & MTIME_PRESENT) != 0)
            mtime = in.readDword();

        if ((flags & ATIME_PRESENT) != 0)
            atime = in.readDword();

        if ((flags & CTIME_PRESENT) != 0)
            ctime = in.readDword();
    }

    @Override
    protected void _writeObject(IDataOut out)
            throws IOException {
        out.writeByte(flags);

        if ((flags & MTIME_PRESENT) != 0)
            out.writeDword(mtime);

        if ((flags & ATIME_PRESENT) != 0)
            out.writeDword(atime);

        if ((flags & CTIME_PRESENT) != 0)
            out.writeDword(ctime);
    }

}
