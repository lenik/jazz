package net.bodz.bas.vfs.impl.apache;

import java.io.IOException;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileTime;

import org.apache.commons.vfs.FileContent;
import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystemException;

import net.bodz.bas.c.java.nio.DeviceAttributeView;
import net.bodz.bas.c.java.nio.DeviceAttributes;
import net.bodz.bas.c.java.nio.FilePermissionAttributeView;
import net.bodz.bas.c.java.nio.FilePermissionAttributes;

public class FileObjectAttributes
        implements BasicFileAttributeView, BasicFileAttributes, //
        DosFileAttributeView, DosFileAttributes, //
        FilePermissionAttributeView, FilePermissionAttributes, //
        DeviceAttributeView, DeviceAttributes {

    private final FileObject fileObject;

    public FileObjectAttributes(FileObject fileObject) {
        if (fileObject == null)
            throw new NullPointerException("fileObject");
        this.fileObject = fileObject;
    }

    @Override
    public String name() {
        return "*";
    }

    @Override
    public FileObjectAttributes readAttributes() {
        return this;
    }

    /** ⇱ Implementaton Of {@link BasicFileAttributes}. */
    /* _____________________________ */static section.iface __BASIC__;

    @Override
    public FileTime lastModifiedTime() {
        long lastModifiedTime;
        try {
            FileContent content = fileObject.getContent();
            lastModifiedTime = content.getLastModifiedTime();
        } catch (FileSystemException e) {
            lastModifiedTime = 0L;
        }
        return FileTime.fromMillis(lastModifiedTime);
    }

    @Override
    public FileTime lastAccessTime() {
        return lastModifiedTime();
    }

    @Override
    public FileTime creationTime() {
        return lastModifiedTime();
    }

    @Override
    public boolean isRegularFile() {
        try {
            return fileObject.getType().hasContent();
        } catch (FileSystemException e) {
            return false;
        }
    }

    @Override
    public boolean isDirectory() {
        try {
            return fileObject.getType().hasChildren();
        } catch (FileSystemException e) {
            return false;
        }
    }

    @Override
    public boolean isSymbolicLink() {
        return false;
    }

    @Override
    public boolean isOther() {
        return false;
    }

    @Override
    public long size() {
        try {
            if (!fileObject.exists())
                return 0L;

            FileContent fileContent = fileObject.getContent();

            long size = fileContent.getSize();
            return size;
        } catch (FileSystemException e) {
            return 0L;
        }
    }

    @Override
    public Object fileKey() {
        return null;
    }

    @Override
    public void setTimes(FileTime lastModifiedTime, FileTime lastAccessTime, FileTime createTime)
            throws IOException {
        try {
            FileContent fileContent = fileObject.getContent();
            fileContent.setLastModifiedTime(lastModifiedTime.toMillis());
        } catch (FileSystemException e) {
        }
    }

    /** ⇱ Implementaton Of {@link DosFileAttributes}. */
    /* _____________________________ */static section.iface __DOS__;

    @Override
    public boolean isReadOnly() {
        return false;
    }

    @Override
    public boolean isHidden() {
        try {
            return fileObject.isHidden();
        } catch (FileSystemException e) {
            return false;
        }
    }

    @Override
    public boolean isArchive() {
        return false;
    }

    @Override
    public boolean isSystem() {
        return false;
    }

    @Override
    public void setReadOnly(boolean value)
            throws IOException {
    }

    @Override
    public void setHidden(boolean value)
            throws IOException {
    }

    @Override
    public void setSystem(boolean value)
            throws IOException {
    }

    @Override
    public void setArchive(boolean value)
            throws IOException {
    }

    /** ⇱ Implementaton Of {@link FilePermissionAttributes}. */
    /* _____________________________ */static section.iface __FILE_PERMISSION__;

    @Override
    public boolean isReadable() {
        try {
            return fileObject.isReadable();
        } catch (FileSystemException e) {
            return false;
        }
    }

    @Override
    public boolean isWritable() {
        try {
            return fileObject.isWriteable();
        } catch (FileSystemException e) {
            return false;
        }
    }

    @Override
    public boolean isExecutable() {
        return false;
    }

    @Override
    public boolean isDeletable() {
        return false;
    }

    /** ⇱ Implementaton Of {@link DeviceAttributes}. */
    /* _____________________________ */static section.iface __DEVICE__;

    @Override
    public boolean isRandomAccessible() {
        return true;
    }

    @Override
    public boolean isBlockAccessible() {
        return true;
    }

}
