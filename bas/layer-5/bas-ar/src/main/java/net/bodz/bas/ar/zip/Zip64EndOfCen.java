package net.bodz.bas.ar.zip;

import java.io.IOException;

import net.bodz.bas.fmt.rst.RstDataStruct;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;

public class Zip64EndOfCen
        extends RstDataStruct {

    private static final long serialVersionUID = 1L;

    public static final int SIG = 0x06064b50;

    public int sig;

    /**
     * size of zip64 end of central directory record.
     * 
     * This should be the size of the remaining record and should not include the leading 12 bytes.
     * 
     * Size = SizeOfFixedFields + SizeOfVariableData - 12.
     * 
     * (12 = signature(4) + size(8))
     */
    public int size;

    public short versionMadeBy;
    public short versionNeeded;

    /** number of this disk */
    public int diskNo;

    /** number of the disk with the start of the central directory */
    public int startOfCenDisk;

    /** total number of entries in the central directory on this disk */
    public long diskEntryCount;

    /** total number of entries in the central directory */
    public long totalEntryCount;

    /** size of the central directory */
    public long sizeOfCen;

    /** offset of start of central directory with respect to the starting disk number */
    public long startCenOffset;

    public byte[] extDataSector;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void readObject(IDataIn in)
            throws IOException {
        sig = in.readDword();
        _readObject(in);
    }

    @Override
    public void writeObject(IDataOut out)
            throws IOException {
        out.writeDword(sig);
        _writeObject(out);
    }

    public void _readObject(IDataIn in)
            throws IOException {

        long sizel = in.readQword();
        if (sizel > Integer.MAX_VALUE)
            throw new UnsupportedOperationException("Size of zip64 EOC is too large: " + sizel);
        else
            size = (int) sizel;

        versionMadeBy = in.readWord();
        versionNeeded = in.readWord();
        diskNo = in.readDword();
        startOfCenDisk = in.readDword();
        diskEntryCount = in.readQword();
        totalEntryCount = in.readQword();
        sizeOfCen = in.readQword();
        startCenOffset = in.readQword();
    }

    public void _writeObject(IDataOut out)
            throws IOException {
        out.writeQword(size);
        out.writeWord(versionMadeBy);
        out.writeWord(versionNeeded);
        out.writeDword(diskNo);
        out.writeDword(startOfCenDisk);
        out.writeQword(diskEntryCount);
        out.writeQword(totalEntryCount);
        out.writeQword(sizeOfCen);
        out.writeQword(startCenOffset);
    }

}
