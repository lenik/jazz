package net.bodz.bas.vfs.impl.zip;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.OpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.ar.zip.ZipEntry;
import net.bodz.bas.ar.zip.xf.XF_UNIX;
import net.bodz.bas.ar.zip.xf3.XF_ASi_UNIX;
import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.java.nio.DeleteOption;
import net.bodz.bas.c.java.nio.DeleteOptions;
import net.bodz.bas.c.java.nio.UnixModeBits;
import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.io.ICroppable;
import net.bodz.bas.io.ISeekable;
import net.bodz.bas.io.res.AbstractIORandomResource;
import net.bodz.bas.io.res.IRandomResource;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.vfs.AbstractFile;
import net.bodz.bas.vfs.IFileAttributes;
import net.bodz.bas.vfs.IFsBlob;
import net.bodz.bas.vfs.IFsDir;
import net.bodz.bas.vfs.IFsObject;
import net.bodz.bas.vfs.VFSException;

public class ZipEntryFile
        extends AbstractFile {

    // private ZipEntryFile parentFile;
    private Map<String, ZipEntryFile> childMap = new TreeMap<String, ZipEntryFile>();

    private ZipEntry zipEntry;

    public ZipEntryFile(ZipVfsDevice device, String entryName)
            throws IOException {
        super(device, FilePath.getBaseName(entryName));
        // XXX should be getZipArchiver.
        this.zipEntry = new ZipEntry(device.archiver());
    }

    /**
     * Initialize for reading.
     *
     * @param entryName
     *            The pathname of a {@link java.io.File}.
     * @throws NullPointerException
     *             if <code>pojfPath</code> is <code>null</code>
     */
    public ZipEntryFile(ZipVfsDevice device, ZipEntry zipEntry) {
        super(device, FilePath.getBaseName(zipEntry.getName()));
        this.zipEntry = zipEntry;
    }

    void detach() {
        ZipEntryFile parentFile = getParentFile();
        if (parentFile != null)
            parentFile.childMap.remove(getName());
    }

    public ZipEntry getZipEntry() {
        return zipEntry;
    }

    public Map<String, ZipEntryFile> getChildMap() {
        return childMap;
    }

    /** ⇱ Implementation Of {@link IFsObject}. */
    /* _____________________________ */static section.iface __FS_OBJ__;

    @Override
    public ZipVfsDevice getDevice() {
        return (ZipVfsDevice) super.getDevice();
    }

    @Override
    public ZipEntryPath getPath() {
        ZipVfsDevice device = getDevice();

        String zipName = device.getDeviceSpec();
        String entryName = zipEntry.getName();

        ZipEntryPath path = new ZipEntryPath(device.getProtocol(), zipName, entryName);
        return path;
    }

    @Override
    public ZipEntryFile getParentFile() {
        String name = zipEntry.getName();
        String dirname = StringPart.beforeLast(name, '/');

        ZipVfsDevice device = getDevice();
        ZipEntryFile parent = (ZipEntryFile) device.resolve(dirname);
        return parent;
    }

    @Override
    public Boolean exists() {
        return zipEntry != null;
    }

    @Override
    public int delete(DeleteOption... options) {
        if (zipEntry == null)
            return 0;

        if (!childMap.isEmpty()) {
            if (!DeleteOptions.isDeleteTree(options))
                return 0;
        }

        int count = 0;
        detach();
        zipEntry = null;

        if (DeleteOptions.isRemoveEmptyParents(options)) {
        }

        return count;
    }

    @Override
    public boolean deleteOnExit(DeleteOption... options) {
        throw new UnsupportedOperationException();
    }

    /** ⇱ Implementaton Of {@link IFsBlob}. */
    /* _____________________________ */static section.iface __BLOB__;

    @Override
    public long getLength() {
        return zipEntry.size;
    }

    @Override
    public boolean setLength(long newLength)
            throws IOException {
        long length = getLength();
        if (newLength == length)
            return true;

        if (!isWritable())
            return false;

        if (newLength < length)
            // trim...
            return false;
        else
            // append NULs..
            return false;
    }

    @Override
    public boolean mkblob(boolean touch)
            throws IOException {
        // getResource();
        // 4 mo.

        return false;
    }

    @Override
    protected IRandomResource _getResource(Charset charset) {
        return new AbstractIORandomResource() {

            @Override
            public boolean isCharInPreferred() {
                return false;
            }

            @Override
            protected InputStream _newInputStream(OpenOption... options)
                    throws IOException {
                return zipEntry.getInputSource().newInputStream(options);
            }

            @Override
            protected OutputStream _newOutputStream(OpenOption... options)
                    throws IOException {
                return null;
            }

            @Override
            protected ISeekable getSeeker() {
                return null;
            }

            @Override
            protected ICroppable getCropper() {
                return null;
            }

        };
    }

    @Override
    protected IStreamInputSource _getInputSource(Charset charset) {
        return zipEntry.getInputSource();
    }

    /** ⇱ Implementaton Of {@link IFsDir}. */
    /* _____________________________ */static section.iface __DIR__;

    @Override
    public ZipEntryFile getChild(String childName) {
        ZipEntryFile child = childMap.get(childName);
        if (child == null) {
            String entryName = zipEntry.getName();
            String localPath = entryName + "/" + childName;
            child = (ZipEntryFile) getDevice().resolve(localPath);
            childMap.put(childName, child);
        }
        return child;
    }

    @Override
    public Iterable<? extends ZipEntryFile> children()
            throws VFSException {
        return childMap.values();
    }

    @Override
    public boolean mkdir() {
        return isDirectory();
    }

    /** ⇱ Implementation Of {@link IFileAttributes}. */
    /* _____________________________ */static section.iface __ATTRIBUTES__;

    @Override
    public boolean isReadable() {
        if (zipEntry == null)
            return false;
        // if (zipEntry.isDirectory())
        // return false;
        return true;
    }

    @Override
    public boolean isWritable() {
        if (zipEntry == null)
            return false;
        // if (zipEntry.isDirectory())
        // return false;
        return true;
    }

    @Override
    public boolean isExecutable() {
        XF_ASi_UNIX unix = zipEntry.extraFields.get(XF_ASi_UNIX.class);
        if (unix != null)
            if ((unix.mode & UnixModeBits.EXECUTE_MASK) != 0)
                return true;
        return false;
    }

    /**
     * Generally, a zip entry can only be accessed in streaming like.
     *
     * However, it may implement the random access interface.
     */
    @Override
    public boolean isRandomAccessible() {
        return false;
    }

    @Override
    public void setTimes(FileTime lastModifiedTime, FileTime lastAccessTime, FileTime createTime)
            throws IOException {
        zipEntry.setCreatedTime(createTime.toMillis());
        zipEntry.setModifiedTime(lastModifiedTime.toMillis());
    }

    /** ⇱ Implementaton Of {@link BasicFileAttributes}. */
    /* _____________________________ */static section.iface __ATTRS_BASIC__;

    @Override
    public FileTime creationTime() {
        return FileTime.fromMillis(zipEntry.getCreatedTime());
    }

    @Override
    public FileTime lastModifiedTime() {
        return FileTime.fromMillis(zipEntry.getModifiedTime());
    }

    @Override
    public boolean isRegularFile() {
        return !zipEntry.isDirectory();
    }

    @Override
    public boolean isDirectory() {
        return zipEntry.isDirectory();
    }

    @Override
    public boolean isSymbolicLink() {
        XF_UNIX _UNIX = zipEntry.extraFields.get(XF_UNIX.class);
        if (_UNIX != null) {
            return _UNIX.data.length != 0;
        }

        XF_ASi_UNIX _ASi_UNIX = zipEntry.extraFields.get(XF_ASi_UNIX.class);
        if (_ASi_UNIX != null) {
            return _ASi_UNIX.targetRaw.length != 0;
        }

        return false;
    }

    @Override
    public boolean isOther() {
        return false;
    }

    @Override
    public long size() {
        return getLength();
    }

    @Override
    public Object fileKey() {
        return this;
    }

    /** ⇱ Implementation Of {@link Object}. */
    /* _____________________________ */static section.obj __OBJ__;

    @Override
    public int hashCode() {
        int hash = 0x4c691595;
        hash += getDevice().hashCode() * 17;
        hash += zipEntry.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ZipEntryFile))
            return false;
        ZipEntryFile o = (ZipEntryFile) obj;
        if (!getDevice().equals(o.getDevice()))
            return false;
        if (!zipEntry.equals(o.zipEntry))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return zipEntry.toString();
    }

}
