package net.bodz.bas.vfs.impl.nio;

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

public class NioExtraFileAttributes
        implements //
        FilePermissionAttributeView, FilePermissionAttributes, //
        DeviceAttributeView, DeviceAttributes {

    private final Path path;
    private transient BasicFileAttributes basicFileAttributes;

    public NioExtraFileAttributes(Path path) {
        if (path == null)
            throw new NullPointerException("path");
        this.path = path;
    }

    synchronized void loadBasicFileAttributes() {
        if (basicFileAttributes == null)
            try {
                BasicFileAttributeView basicFileAttributeView = Files.getFileAttributeView(path,
                        BasicFileAttributeView.class);
                basicFileAttributes = basicFileAttributeView.readAttributes();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
    }

    @Override
    public String name() {
        return "*";
    }

    @Override
    public NioExtraFileAttributes readAttributes()
            throws IOException {
        return this;
    }

    // -o BasicFileAttributes

    @Override
    public FileTime lastModifiedTime() {
        loadBasicFileAttributes();
        return basicFileAttributes.lastModifiedTime();
    }

    @Override
    public FileTime lastAccessTime() {
        loadBasicFileAttributes();
        return basicFileAttributes.lastAccessTime();
    }

    @Override
    public FileTime creationTime() {
        loadBasicFileAttributes();
        return basicFileAttributes.creationTime();
    }

    @Override
    public boolean isRegularFile() {
        loadBasicFileAttributes();
        return basicFileAttributes.isRegularFile();
    }

    @Override
    public boolean isDirectory() {
        loadBasicFileAttributes();
        return basicFileAttributes.isDirectory();
    }

    @Override
    public boolean isSymbolicLink() {
        loadBasicFileAttributes();
        return basicFileAttributes.isSymbolicLink();
    }

    @Override
    public boolean isOther() {
        loadBasicFileAttributes();
        return basicFileAttributes.isOther();
    }

    @Override
    public long size() {
        loadBasicFileAttributes();
        return basicFileAttributes.size();
    }

    @Override
    public Object fileKey() {
        loadBasicFileAttributes();
        return basicFileAttributes.fileKey();
    }

    // -o FilePermissionAttributes

    @Override
    public boolean isReadable() {
        return Files.isReadable(path);
    }

    @Override
    public boolean isWritable() {
        return Files.isWritable(path);
    }

    @Override
    public boolean isExecutable() {
        return Files.isExecutable(path);
    }

    @Override
    public boolean isDeletable() {
        Path parentPath = path.getParent();
        if (parentPath == null)
            return false;
        File parentFile = parentPath.toFile();
        if (parentFile == null)
            return false;
        return parentFile.canWrite();
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
