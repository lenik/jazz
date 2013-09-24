package net.bodz.bas.ar.zip;

import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.text.rst.RstObject;

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
    public int time_dos;
    public int crc32;
    public long compressedSize;
    public long size;

    public byte[] nameRaw;
    public byte[] extras;
    public byte[] commentRaw;

    /** Appeared in central directory header only. */
    public short startDisk;

    /** Appeared in central directory header only. */
    public short internalFileAttributes;

    /** Appeared in central directory header only. */
    public int externalFileAttributes;

    /** Appeared in central directory header only. */
    public int localHeaderOffset;

    private transient Charset charset;
    private transient String name;
    transient ExtraFieldMap extraFields;
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
        _readNameAndExtras(in);
    }

    public void _readCen(IDataIn in)
            throws IOException {
        versionMadeBy = in.readWord();
        _readShared(in);

        int commentLen = in.readWord() & 0xffff;
        commentRaw = new byte[commentLen];

        startDisk = in.readWord();
        internalFileAttributes = in.readWord();
        externalFileAttributes = in.readDword();
        localHeaderOffset = in.readDword();

        _readNameAndExtras(in);
        _readComment(in);

        dataAddress = -1L;
    }

    void _readShared(IDataIn in)
            throws IOException {
        versionNeeded = in.readWord();
        ctx.requireZipVersion(versionNeeded);

        flags = in.readWord();
        charset = (flags & F_UTF8) == 0 ? ctx.getZipCharset() : utf8Charset;

        method = in.readWord();
        time_dos = in.readDword();

        crc32 = in.readDword();
        compressedSize = in.readDword();
        size = in.readDword();

        if ((flags & F_DATA_DESCRIPTOR) != 0) {
            assert crc32 == 0;
            assert compressedSize == 0;
            assert size == 0;
        }

        int nameLen = in.readWord() & 0xffff;
        nameRaw = new byte[nameLen];
        name = null;

        int extrasLen = in.readWord() & 0xffff;
        extras = new byte[extrasLen];
    }

    void _readNameAndExtras(IDataIn in)
            throws IOException {
        in.readBytes(nameRaw);
        in.readBytes(extras);
        extraFields = ExtraFieldMap.parse(extras);
    }

    void _readComment(IDataIn in)
            throws IOException {
        in.readBytes(commentRaw);
        comment = null;
    }

    /** ⇱ Implementation Of {@link IZipEntry}. */
    ;

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
    ;

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
        return getModifiedTime();
    }

    @Override
    public long getModifiedTime() {
        long time = TimeUtils.fromDos(time_dos);
        return time;
    }

    public void setModifiedTime(long time) {
        time_dos = TimeUtils.toDos(time);
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
    ;

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
