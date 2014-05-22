package net.bodz.bas.ar.zip;

import java.io.IOException;

import net.bodz.bas.fmt.rst.RstDataStruct;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;

public class Zip64EndOfCenLocator
        extends RstDataStruct {

    private static final long serialVersionUID = 1L;

    public static final int SIG = 0x07064b50;

    public int sig;

    /** number of the disk with the start of the zip64 end of central directory */
    public int startDisk;

    /** relative offset of the zip64 end of central directory record */
    public long offset;

    /** total number of disks */
    public int diskCount;

    @Override
    public int size() {
        return 20;
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
        startDisk = in.readDword();
        offset = in.readQword();
        diskCount = in.readDword();
    }

    public void _writeObject(IDataOut out)
            throws IOException {
        out.writeDword(startDisk);
        out.writeQword(offset);
        out.writeDword(diskCount);
    }

}
