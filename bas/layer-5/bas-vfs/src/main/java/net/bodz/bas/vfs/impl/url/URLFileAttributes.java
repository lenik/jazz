package net.bodz.bas.vfs.impl.url;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

import net.bodz.bas.c.java.io.FileURL;
import net.bodz.bas.c.java.nio.DeviceAttributeView;
import net.bodz.bas.c.java.nio.DeviceAttributes;
import net.bodz.bas.c.java.nio.FilePermissionAttributeView;
import net.bodz.bas.c.java.nio.FilePermissionAttributes;

public class URLFileAttributes
        implements //
        BasicFileAttributeView, BasicFileAttributes, //
        FilePermissionAttributeView, FilePermissionAttributes, //
        DeviceAttributeView, DeviceAttributes {

    private URLFile urlFile;
    private URL url;

    public URLFileAttributes(URLFile urlFile) {
        this.urlFile = urlFile;
        this.url = urlFile.getPath().toURL();
    }

    @Override
    public String name() {
        return "*";
    }

    @Override
    public URLFileAttributes readAttributes()
            throws IOException {
        return this;
    }

    // -o BasicFileAttributes and View

    @Override
    public FileTime creationTime() {
        long creationTime = 0L;
        try {
            URLConnection urlConnection = url.openConnection();
            // urlConnection.getHeaderField("Create-Time");
            creationTime = urlConnection.getLastModified();
        } catch (IOException e) {
        }
        return FileTime.fromMillis(creationTime);
    }

    @Override
    public FileTime lastModifiedTime() {
        long lastModified = 0L;
        try {
            lastModified = url.openConnection().getLastModified();
        } catch (IOException e) {
        }
        return FileTime.fromMillis(lastModified);
    }

    @Override
    public FileTime lastAccessTime() {
        return lastModifiedTime();
    }

    @Override
    public boolean isRegularFile() {
        switch (url.getProtocol()) {
        case "file":
            File file = FileURL.toFile(url);
            return file.isFile();

        default:
            return true;
        }
    }

    @Override
    public boolean isDirectory() {
        switch (url.getProtocol()) {
        case "file":
            File file = FileURL.toFile(url);
            return file.isDirectory();

        default:
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
        return urlFile.length();
    }

    @Override
    public Object fileKey() {
        return url.toString();
    }

    @Override
    public void setTimes(FileTime lastModifiedTime, FileTime lastAccessTime, FileTime createTime)
            throws IOException {
        switch (url.getProtocol()) {
        case "file":
            File file = FileURL.toFile(url);
            file.setLastModified(lastModifiedTime.toMillis());
            break;
        }
    }

    // -o FilePermissionAttributes

    @Override
    public boolean isReadable() {
        switch (url.getProtocol()) {
        case "file":
            File file = FileURL.toFile(url);
            return file.canRead();
        default:
            return true;
        }
    }

    @Override
    public boolean isWritable() {
        switch (url.getProtocol()) {
        case "file":
            File file = FileURL.toFile(url);
            return file.canWrite();
        default:
            return false;
        }
    }

    @Override
    public boolean isExecutable() {
        switch (url.getProtocol()) {
        case "file":
            File file = FileURL.toFile(url);
            return file.canExecute();
        default:
            return false;
        }
    }

    @Override
    public boolean isDeletable() {
        switch (url.getProtocol()) {
        case "file":
            File file = FileURL.toFile(url);
            File parentFile = file.getParentFile();
            if (parentFile != null)
                return parentFile.canWrite();
            else
                return false;

        default:
            return false;
        }
    }

    // -o DeviceAttributes

    @Override
    public boolean isRandomAccessible() {
        switch (url.getProtocol()) {
        case "file":
        case "jar":
            return true;
        default:
            return false;
        }
    }

    @Override
    public boolean isBlockAccessible() {
        switch (url.getProtocol()) {
        case "file":
        case "jar":
            return true;
        default:
            return false;
        }
    }

}
