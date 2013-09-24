package net.bodz.bas.ar.zip.xf;

import java.io.IOException;

import net.bodz.bas.ar.zip.ExtraField;
import net.bodz.bas.ar.zip.ExtraFieldType;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;

/**
 * The OS/2 extended attribute structure (FEA2LIST) is compressed and then stored in its entirety
 * within this structure. There will only ever be one "block" of data in VarFields[].
 */
@ExtraFieldType(id = 0x0009)
public class XF_OS2
        extends ExtraField {

    private static final long serialVersionUID = 1L;

    /** uncompressed block size */
    public int blockSize;

    public short compressionType;

    /** CRC value for uncompressed block */
    public int eacrc;

    public byte[] compressedBlock;

    @Override
    protected void _readObject(IDataIn in)
            throws IOException {
        blockSize = in.readDword();
        compressionType = in.readWord();
        eacrc = in.readDword();
        compressedBlock = new byte[size - 10];
        in.readBytes(compressedBlock);
    }

    @Override
    protected void _writeObject(IDataOut out)
            throws IOException {
        out.writeDword(blockSize);
        out.writeWord(compressionType);
        out.writeDword(eacrc);
        out.write(compressedBlock);
    }

}
