package net.bodz.bas.ar.zip;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.ar.IArchiveEntry;
import net.bodz.bas.ar.IUnarchiver;
import net.bodz.bas.c.java.io.RafInputStream;
import net.bodz.bas.err.BadFormatException;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.ICroppable;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.ISeekable;
import net.bodz.bas.io.data.DataInImplLE;

public class ZipUnarchiver
        implements IUnarchiver, IZipConsts, IZip64Consts {

    IDataIn in;
    ISeekable seeker;
    ICroppable cropper;

    Charset charset = Charset.defaultCharset();
    Map<String, IZipEntry> entryMap = new LinkedHashMap<>();

    byte[] tmpbuf = new byte[128];

    public <T extends IByteIn & ISeekable & ICroppable> ZipUnarchiver(T in) {
        if (in == null)
            throw new NullPointerException("in");
        this.in = DataInImplLE.from(in);
        this.seeker = in;
        this.cropper = in;
    }

    @Override
    public void close()
            throws IOException {
        in.close();
    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public Iterable<? extends IArchiveEntry> entries()
            throws IOException {
        return null;
    }

    @Override
    public IArchiveEntry getEntry(String name)
            throws IOException {
        return null;
    }

    public ZipEntry getNextEntry()
            throws IOException {

        int sig = in.readDword();
        if (sig != LFH_SIG)
            throw new BadFormatException("Invalid zip signature: " + sig);

        int versionNeeded = in.readWord() & 0xffff;
        if (versionNeeded >= VN_Encrypted)
            throw new UnsupportedOperationException("Version too high to handle: " + versionNeeded);

        ZipEntry entry = new ZipEntry(this);

        int flags = in.readWord();
        Charset charset = (flags & F_UTF8) == 0 ? this.charset : utf8Charset;

        entry.method = in.readWord();
        entry.time = in.readDword();
        entry.crc32 = in.readDword();
        entry.compressedSize = in.readDword();
        entry.size = in.readDword();

        int fileNameLen = in.readWord() & 0xffff;
        int extraFieldLen = in.readWord() & 0xffff;

        alloctmp(fileNameLen);
        in.readBytes(tmpbuf, 0, fileNameLen);
        String fileName = new String(tmpbuf, 0, fileNameLen, charset);
        entry.directory = fileName.endsWith("/");
        // if (entry.directory) fileName = fileName.substring(0, fileNameLen - 1);
        entry.name = fileName;

        alloctmp(extraFieldLen);
        in.readBytes(tmpbuf, 0, extraFieldLen);
        // extra fields are skipped here.
        entry.extraBytes = Arrays.copyOf(tmpbuf, extraFieldLen);

        entry.offset = seeker.tell();
        seeker.seek(entry.offset + entry.compressedSize);
        return entry;
    }

    void alloctmp(int size) {
        if (tmpbuf.length < size)
            tmpbuf = new byte[size];
    }

    public static void main(String[] args)
            throws Exception {
        RafInputStream in = new RafInputStream("/tmp/b.zip", "r");
        ZipUnarchiver unarchiver = new ZipUnarchiver(in);
        ZipEntry entry;
        while ((entry = unarchiver.getNextEntry()) != null) {
            System.out.println(entry.getName() + ": " + entry.extraBytes.length);
        }
    }

}
