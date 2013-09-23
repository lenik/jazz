package net.bodz.bas.ar.zip.xf3;

import java.io.IOException;

import net.bodz.bas.ar.zip.ExtraField;
import net.bodz.bas.ar.zip.ExtraFieldType;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;

/**
 * The following is the layout of the ZipIt extra block for Macintosh. The local-header and
 * central-header versions are identical. This block must be present if the file is stored
 * MacBinary-encoded and it should not be used if the file is not stored MacBinary-encoded.
 */
@ExtraFieldType(id = 0x2605, sizeTotal = true)
public class XF_ZipIt_Mac26
        extends ExtraField {

    private static final long serialVersionUID = 1L;

    /** big-endian "ZPIT" */
    public int sig;

    public byte fileNameLen;
    public byte[] fileNameRaw;

    /** four-byte Mac file type string */
    public int fileType;
    /** four-byte Mac creator string */
    public int creator;

    @Override
    protected void _readObject(IDataIn in)
            throws IOException {
        sig = in.readDword();
        fileNameLen = in.readByte();
        fileNameRaw = new byte[fileNameLen];
        in.readBytes(fileNameRaw);
        fileType = in.readDword();
        creator = in.readDword();
    }

    @Override
    protected void _writeObject(IDataOut out)
            throws IOException {
        out.writeDword(sig);
        out.writeByte(fileNameLen);
        out.write(fileNameRaw);
        out.writeDword(fileType);
        out.writeDword(creator);
    }

}
