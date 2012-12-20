package net.bodz.bas.vfs.impl.jdk;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

import net.bodz.bas.c.java.nio.DeviceAttributeView;
import net.bodz.bas.c.java.nio.DeviceAttributes;
import net.bodz.bas.c.java.nio.FilePermissionAttributeView;
import net.bodz.bas.c.java.nio.FilePermissionAttributes;

public class JdkFileAttributes
        implements //
        BasicFileAttributeView, BasicFileAttributes, //
        FilePermissionAttributeView, FilePermissionAttributes, //
        DeviceAttributeView, DeviceAttributes {

    private File file;

    public JdkFileAttributes(File file) {
        if (file == null)
            throw new NullPointerException("file");
        this.file = file;
    }

    @Override
    public String name() {
        return "*";
    }

    @Override
    public JdkFileAttributes readAttributes() {
        return this;
    }

    // -o BasicFileAttributes and View

    @Override
    public FileTime lastModifiedTime() {
        return FileTime.fromMillis(file.lastModified());
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
        return file.isFile();
    }

    @Override
    public boolean isDirectory() {
        return file.isDirectory();
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
        return file.length();
    }

    @Override
    public Object fileKey() {
        Path path = file.toPath();
        BasicFileAttributeView view = Files.getFileAttributeView(path, BasicFileAttributeView.class);
        if (view == null)
            return null;
        try {
            return view.readAttributes().fileKey();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void setTimes(FileTime lastModifiedTime, FileTime lastAccessTime, FileTime createTime)
            throws IOException {
        file.setLastModified(lastModifiedTime.toMillis());
    }

    // -o FilePermissionAttributes

    @Override
    public boolean isReadable() {
        return file.canRead();
    }

    @Override
    public boolean isWritable() {
        return file.canWrite();
    }

    @Override
    public boolean isExecutable() {
        return file.canExecute();
    }

    @Override
    public boolean isDeletable() {
        File parentFile = file.getParentFile();
        if (parentFile != null)
            return parentFile.canWrite();
        else
            return false;
    }

    // -o DeviceAttributes

    @Override
    public boolean isRandomAccessible() {
        return true;
    }

    @Override
    public boolean isBlockAccessible() {
        return true;
    }

}
