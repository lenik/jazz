package net.bodz.bas.ar.zip.xf;

import java.io.IOException;

import net.bodz.bas.ar.zip.ExtraField;
import net.bodz.bas.ar.zip.ExtraFieldType;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;

/**
 * Zip64 extended information extra field
 * 
 * The following is the layout of the zip64 extended information "extra" block. If one of the size
 * or offset fields in the Local or Central directory record is too small to hold the required data,
 * a Zip64 extended information record is created. The order of the fields in the zip64 extended
 * information record is fixed, but the fields MUST only appear if the corresponding Local or
 * Central directory record field is set to 0xFFFF or 0xFFFFFFFF.
 * 
 * This entry in the Local header MUST include BOTH original and compressed file size fields. If
 * encrypting the central directory and bit 13 of the general purpose bit flag is set indicating
 * masking, the value stored in the Local Header for the original file size will be zero.
 */
@ExtraFieldType(id = 0x0001, sizeTotal = true)
public class XF_Zip64
        extends ExtraField {

    private static final long serialVersionUID = 1L;

    public long size;
    public long compressedSize;

    /** Offset of local header record */
    public long offset;

    /** Number of the disk on which this file starts */
    public int startDisk;

    @Override
    protected void _readObject(IDataIn in)
            throws IOException {
        size = in.readQword();
        compressedSize = in.readQword();
        offset = in.readQword();
        startDisk = in.readDword();
    }

    @Override
    protected void _writeObject(IDataOut out)
            throws IOException {
        out.writeQword(size);
        out.writeQword(compressedSize);
        out.writeQword(offset);
        out.writeDword(startDisk);
    }

}
