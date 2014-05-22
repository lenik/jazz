package net.bodz.bas.ar.zip;

import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.ar.IArchiveEntry;
import net.bodz.bas.ar.zip.xf.XF_UNIX;
import net.bodz.bas.ar.zip.xf.XF_Zip64;
import net.bodz.bas.ar.zip.xf3.XF_ASi_UNIX;
import net.bodz.bas.ar.zip.xf3.XF_InfoZip_UNIX2;
import net.bodz.bas.ar.zip.xf3.XF_InfoZip_UnicodePath;
import net.bodz.bas.ar.zip.xf3.XF_Timestamp;
import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.fmt.rst.RstObject;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.io.res.IStreamInputSourceWrapper;
import net.bodz.bas.typer.std.ValidationException;

public class ZipEntry
        extends RstObject
        implements IZipEntry/* , IDataStruct */{

    public static final int SIG_CEN = 0x02014b50;
    public static final int SIG_LFH = 0x04034b50;

    private transient IZipContext ctx;

    public int sig;

    /** Appeared in central directory header only. */
    public short versionMadeBy;

    public short versionNeeded;
    public short flags;
    public short method;

    /** Appeared in XF_Timestamp header only. */
    public/* transient */int ctime;
    public int mtime;

    public int crc32;

    /**
     * The size of the file compressed (4.4.8) and uncompressed, (4.4.9) respectively. When a
     * decryption header is present it will be placed in front of the file data and the value of the
     * compressed file size will include the bytes of the decryption header. If bit 3 of the general
     * purpose bit flag is set, these fields are set to zero in the local header and the correct
     * values are put in the data descriptor and in the central directory. If an archive is in ZIP64
     * format and the value in this field is 0xFFFFFFFF, the size will be in the corresponding 8
     * byte ZIP64 extended information extra field. When encrypting the central directory, if the
     * local header is not in ZIP64 format and general purpose bit flag 13 is set indicating
     * masking, the value stored for the uncompressed size in the Local Header will be zero.
     */
    public long compressedSize;
    public long size;

    public byte[] nameRaw;
    public byte[] extras;

    /** Appeared in local file header only. */
    public byte[] encryptionHeader;

    /** Appeared in central directory header only. */
    public byte[] commentRaw;

    /**
     * Appeared in:
     * <ul>
     * <li>central directory header: u16
     * <li>zip64 field: u32
     * </ul>
     */
    public int startDisk;

    /** Appeared in central directory header only. */
    public short internalFileAttributes;

    /** Appeared in central directory header only. */
    public int externalFileAttributes;

    /** Appeared in central directory header only. */
    public long localHeaderOffset;

    private transient Charset charset;
    private transient String name;
    public transient ExtraFieldMap extraFields;
    private transient String password;
    private transient ZipEncryptKey encryptKey;
    private transient String comment;

    private transient int perm = 0644;
    private transient Integer uid;
    private transient Integer gid;

    transient long dataAddress;

    public ZipEntry(IZipContext ctx) {
        if (ctx == null)
            throw new NullPointerException("ctx");
        this.ctx = ctx;
    }

    public boolean isEncrytped() {
        return (flags & F_ENC) != 0;
    }

    public ZipEncryptKey getEncryptKey() {
        if (!isEncrytped())
            return null;

        if (encryptKey == null) {
            String password = getPassword();
            encryptKey = new ZipEncryptKey(password);

            ZipEncryptKey zek = encryptKey;
            zek.decrypt(encryptionHeader);

            try {
                zek.validateEH(encryptionHeader, this);
            } catch (ValidationException e1) {
                System.err.println("eh validation failed.");
            }
        }
        return encryptKey.clone();
    }

    public boolean isDDExisted() {
        return (flags & F_DATA_DESCRIPTOR) != 0;
    }

    public void readLoc(IDataIn in)
            throws IOException {
        sig = in.readDword();
        assert sig == SIG_LFH;
        _readLoc(in);
    }

    public void readCen(IDataIn in)
            throws IOException {
        sig = in.readDword();
        assert sig == SIG_CEN;
        _readCen(in);
    }

    public void _readLoc(IDataIn in)
            throws IOException {
        _readShared(in);
        _readNameExtras(in);

        if (isEncrytped())
            in.readBytes(encryptionHeader = new byte[12]);
        else
            encryptionHeader = null;
        encryptKey = null;
    }

    public void _readCen(IDataIn in)
            throws IOException {
        versionMadeBy = in.readWord();
        _readShared(in);

        int commentLen = in.readWord() & 0xffff;
        commentRaw = new byte[commentLen];

        startDisk = in.readWord() & 0xffff;
        internalFileAttributes = in.readWord();
        externalFileAttributes = in.readDword();
        localHeaderOffset = in.readDword() & 0xffffffffL;

        _readNameExtras(in);
        _readComment(in);

        dataAddress = -1L;
    }

    void _readShared(IDataIn in)
            throws IOException {
        versionNeeded = in.readWord();
        ctx.requireZipVersion(versionNeeded);

        flags = in.readWord();
        charset = (flags & F_UTF8) == 0 ? ctx.getZipCharset() : Charsets.UTF8;

        method = in.readWord();
        mtime = in.readDword();

        // if ((flags & F_DATA_DESCRIPTOR) != 0) in.skip(12); else
        crc32 = in.readDword();
        compressedSize = in.readDword() & 0xffffffffL;
        size = in.readDword() & 0xffffffffL;

        int nameLen = in.readWord() & 0xffff;
        nameRaw = new byte[nameLen];
        name = null;

        int extrasLen = in.readWord() & 0xffff;
        extras = new byte[extrasLen];
    }

    void _readNameExtras(IDataIn in)
            throws IOException {
        in.readBytes(nameRaw);
        in.readBytes(extras);

        /*
         * Optim: It seems extra fields can be defined on both LOC and CEN. If it is already loaded
         * from CEN, don't load them again in LOC.
         */
        if (extraFields == null) {
            extraFields = ExtraFieldMap.parse(extras);

            XF_Zip64 _zip64 = extraFields.get(XF_Zip64.class);
            if (_zip64 != null) {
                compressedSize = _zip64.compressedSize;
                size = _zip64.size;
                localHeaderOffset = _zip64.offset;
                startDisk = _zip64.startDisk;
            }

            XF_InfoZip_UnicodePath _unicodePath = extraFields.get(XF_InfoZip_UnicodePath.class);
            if (_unicodePath != null)
                name = _unicodePath.getFileName();

            XF_Timestamp _timestamp = extraFields.get(XF_Timestamp.class);
            if (_timestamp != null) {
                ctime = _timestamp.ctime;
                mtime = _timestamp.mtime;
            }

            XF_UNIX _unix = extraFields.get(XF_UNIX.class);
            if (_unix != null) {
                uid = _unix.uid & 0xffff;
                gid = _unix.gid & 0xffff;
                // atime = _unix.atime;
                // mtime = _unix.mtime;
            }

            XF_ASi_UNIX _asiUnix = extraFields.get(XF_ASi_UNIX.class);
            if (_asiUnix != null) {
                uid = _asiUnix.uid & 0xffff;
                gid = _asiUnix.gid & 0xffff;
                perm = _asiUnix.mode;
                // symlink = _asiUnix.getTarget();
            }

            XF_InfoZip_UNIX2 _unix2 = extraFields.get(XF_InfoZip_UNIX2.class);
            if (_unix2 != null)
                ;
        }
    }

    void _readComment(IDataIn in)
            throws IOException {
        in.readBytes(commentRaw);
        comment = null;
    }

    /** ⇱ Implementation Of {@link IZipEntry}. */
    /* _____________________________ */static section.iface __ZIPENTRY__;

    @Override
    public int getMethod() {
        return method;
    }

    @Override
    public int getCrc32() {
        return crc32;
    }

    @Override
    public long getOffset() {
        return dataAddress;
    }

    /** ⇱ Implementation Of {@link IArchiveEntry}. */
    /* _____________________________ */static section.iface __ARCHIVE_ENTRY__;

    @Override
    public String getName() {
        if (name == null)
            name = new String(nameRaw, charset);
        return name;
    }

    public void setName(String name) {
        this.name = name;
        nameRaw = name.getBytes(charset);
    }

    @Override
    public boolean isDirectory() {
        return name.endsWith("/");
    }

    public String getPassword() {
        if (password == null)
            return ctx.getZipPassword();
        else
            return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getComment() {
        if (comment == null)
            comment = new String(commentRaw, charset);
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
        commentRaw = comment.getBytes(charset);
    }

    @Override
    public long getCreatedTime() {
        int time = ctime != 0 ? ctime : mtime;
        return TimeUtils.fromDos(time);
    }

    public void setCreatedTime(long time) {
        ctime = TimeUtils.toDos(time);
    }

    @Override
    public long getModifiedTime() {
        long time = TimeUtils.fromDos(mtime);
        return time;
    }

    public void setModifiedTime(long time) {
        mtime = TimeUtils.toDos(time);
    }

    @Override
    public long getArchivedSize() {
        return compressedSize;
    }

    @Override
    public long getSize() {
        return size;
    }

    @Override
    public int getMode() {
        return perm;
    }

    @Override
    public Integer getOwnerId() {
        return uid;
    }

    @Override
    public String getOwner() {
        return null;
    }

    @Override
    public Integer getGroupId() {
        return gid;
    }

    @Override
    public String getGroup() {
        return null;
    }

    /** ⇱ Implementation Of {@link IStreamInputSourceWrapper}. */
    /* _____________________________ */static section.iface __INPUT_SOURCE__;

    @Override
    public final IStreamInputSource getInputSource() {
        return getInputSource(charset);
    }

    @Override
    public final IStreamInputSource getInputSource(String charsetName) {
        Charset charset = Charset.forName(charsetName);
        return getInputSource(charset);
    }

    @Override
    public IStreamInputSource getInputSource(Charset charset) {
        ZipEntrySource src = new ZipEntrySource(ctx, this);
        src.setCharset(charset);
        return src;
    }

}
