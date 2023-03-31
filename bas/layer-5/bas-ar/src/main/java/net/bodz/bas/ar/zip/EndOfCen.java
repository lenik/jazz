package net.bodz.bas.ar.zip;

import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.fmt.rst.RstDataStruct;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;

public class EndOfCen
        extends RstDataStruct
        implements IZipConsts {

    private static final long serialVersionUID = 1L;

    public static final int SIG = 0x06054b50;
    public static final byte[] SIG_BYTES = { 'P', 'K', 5, 6 };

    public static final int FIXED_SIZE = 22;
    public static final int COMMENT_SIZE_OFFSET = 20;

    public int sig;

    /** number of this disk */
    public short diskNo;

    /** number of the disk with the start of the central directory */
    public short startOfCenDisk;

    /**
     * total number of entries in the central directory on this disk
     * 
     * Set to -1 if there are too many entries.
     */
    public int/* short */diskEntryCount;

    /**
     * total number of entries in the central directory.
     * 
     * Set to -1 if there are too many entries.
     * 
     * Set to -1 if the Central Directory is compressed and/or encrypted.
     */
    public int/* short */totalEntryCount;

    /** size of the central directory */
    public int sizeOfCen;

    /**
     * offset of start of central directory with respect to the starting disk number
     * 
     * Set to -1 if the value is too large.
     */
    public long /* int */startCenOffset;

    public byte[] commentRaw;

    public transient Charset charset = Charsets.UTF_8;

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
        diskNo = in.readWord();
        startOfCenDisk = in.readWord();
        diskEntryCount = in.readWord() & 0xffff;
        totalEntryCount = in.readWord() & 0xffff;
        sizeOfCen = in.readDword();
        startCenOffset = in.readDword() & 0xffffffffL;

        int commentLen = in.readWord();
        commentRaw = new byte[commentLen];
        in.readBytes(commentRaw);
    }

    public void _writeObject(IDataOut out)
            throws IOException {
        out.writeWord(diskNo);
        out.writeWord(startOfCenDisk);
        out.writeWord(diskEntryCount);
        out.writeWord(totalEntryCount);
        out.writeDword(sizeOfCen);
        out.writeDword((int) startCenOffset);
        out.writeWord(commentRaw.length);
        out.write(commentRaw);
    }

    public String getComment() {
        return new String(commentRaw, charset);
    }

    public void setComment(String comment) {
        commentRaw = comment.getBytes(charset);
    }

}
