package net.bodz.bas.ar.zip;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.ar.IArchiveEntry;
import net.bodz.bas.ar.IUnarchiver;
import net.bodz.bas.c.java.util.Arrays;
import net.bodz.bas.err.BadFormatException;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.ICroppable;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.ISeekable;
import net.bodz.bas.io.data.DataInImplLE;
import net.bodz.bas.io.res.IStreamResource;

public class ZipUnarchiver
        implements IUnarchiver, IZipContext, IZipConsts, IZip64Consts {

    private IDataIn in;
    private ISeekable seeker;
    private ICroppable cropper;
    private long zipLength;

    private Charset charset = Charset.defaultCharset();
    private ZipEntry lastEntry;

    private EndOfCen eoc;
    private Map<String, ZipEntry> centralDir;

    public <T extends IByteIn & ISeekable & ICroppable> ZipUnarchiver(T in)
            throws IOException {
        if (in == null)
            throw new NullPointerException("in");
        this.in = DataInImplLE.from(in);
        this.seeker = in;
        this.cropper = in;
        this.zipLength = seeker.length();
    }

    /** ⇱ Implementation Of {@link IUnarchiver}. */
    ;

    @Override
    public Iterable<? extends ZipEntry> entries()
            throws IOException {
        loadCentralDir();
        return centralDir.values();
    }

    @Override
    public IArchiveEntry getEntry(String name)
            throws IOException {
        loadCentralDir();
        return centralDir.get(name);
    }

    public ZipEntry nextEntry()
            throws IOException {
        if (lastEntry != null) {
            long dataEndPtr = lastEntry.dataAddress + lastEntry.compressedSize;
            seeker.seek(dataEndPtr);

            if ((lastEntry.flags & F_DATA_DESCRIPTOR) != 0) {
                lastEntry.crc32 = in.readDword();
                lastEntry.compressedSize = in.readDword();
                lastEntry.size = in.readDword();
            }
        }

        int sig = in.readDword();
        if (sig != ZipEntry.SIG_LFH) {
            seeker.seek(seeker.tell() - 4);
            return null;
        }

        ZipEntry entry = new ZipEntry(this);
        entry.sig = sig;
        entry._readLoc(in);

        // security-descriptor...

        entry.dataAddress = tell();

        return lastEntry = entry;
    }

    /** ⇱ Implementation Of {@link ICloseable}. */
    ;

    @Override
    public void close()
            throws IOException {
        in.close();
    }

    @Override
    public boolean isClosed() {
        return false;
    }

    /** ⇱ Implementation Of {@link IZipContext}. */
    ;

    @Override
    public long tell() {
        return seeker.tell();
    }

    @Override
    public IStreamResource crop(long start, long end)
            throws IOException {
        return cropper.crop(start, end);
    }

    @Override
    public Charset getZipCharset() {
        return charset;
    }

    @Override
    public long getZipLength() {
        return zipLength;
    }

    @Override
    public void requireZipVersion(short version) {
        if (version > VN_Deflate64)
            throw new UnsupportedOperationException("Version too high to handle: " + version);
    }

    @Override
    public void reloadLFH(ZipEntry entry)
            throws IOException {
        seeker.seek(entry.localHeaderOffset);
        entry.readLoc(in);

        // security-descriptor...

        entry.dataAddress = seeker.tell();
    }

    /** ⇱ Internal Implementations */
    ;

    EndOfCen getEndOfCen()
            throws IOException {
        if (eoc == null) {
            eoc = findEndOfCen();
            if (eoc == null)
                /**
                 * End-of-central-directory signature not found. Either this file is not a zipfile,
                 * or it constitutes one disk of a multi-part archive. In the latter case the
                 * central directory and zipfile comment will be found on the last disk(s) of this
                 * archive.
                 */
                throw new BadFormatException("Invalid zip file: no End-Of-Central-Dir is found. ");
        }
        return eoc;
    }

    private EndOfCen findEndOfCen()
            throws IOException {
        byte[] page = new byte[1024];
        long zipFileSize = seeker.length();
        long ptr = seeker.length() - page.length;

        while (true) {
            if (ptr < 0)
                ptr = 0;

            seeker.seek(ptr);
            int end = in.read(page);

            while (end >= EndOfCen.FIXED_SIZE) {
                int ind = Arrays.lastIndexOf(page, 0, end, EndOfCen.SIG_BYTES);
                if (ind == -1)
                    break;

                long eocPtr = ptr + ind;
                long commentSizePtr = eocPtr + EndOfCen.COMMENT_SIZE_OFFSET;
                seeker.seek(commentSizePtr);
                int commentSize = in.readWord() & 0xffff;
                if (commentSize != zipFileSize - commentSizePtr - 2) { // invalid EOC. record.
                    end = ind;
                    continue;
                }

                seeker.seek(eocPtr);
                EndOfCen eoc = new EndOfCen();
                eoc.readObject(in);
                return eoc;
            }

            if (ptr == 0)
                break;
            ptr -= page.length - EndOfCen.FIXED_SIZE; // a little overlapped.
        }
        return null;
    }

    private void loadCentralDir()
            throws IOException {
        if (centralDir != null)
            return;

        EndOfCen eoc = getEndOfCen();
        seeker.seek(eoc.startCenOffset);

        centralDir = new LinkedHashMap<String, ZipEntry>();

        int sig = in.readDword();
        while (sig == ZipEntry.SIG_CEN) {
            ZipEntry entry = new ZipEntry(this);
            entry._readCen(in);

            centralDir.put(entry.getName(), entry);

            sig = in.readDword();
        }

        int entryCount = centralDir.size();
        assert entryCount >= eoc.diskEntryCount;
    }

}
