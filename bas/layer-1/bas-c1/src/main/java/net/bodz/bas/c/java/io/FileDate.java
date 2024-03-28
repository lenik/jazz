package net.bodz.bas.c.java.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class FileDate {

    public static ZonedDateTime getLastModified(File file) {
        return getLastModified(file, ZoneId.systemDefault());
    }

    public static ZonedDateTime getLastModified(File file, ZoneId zoneId) {
        long epochMilli = file.lastModified();
        Instant instant = Instant.ofEpochMilli(epochMilli);
        ZonedDateTime lastModified = ZonedDateTime.ofInstant(instant, zoneId);
        return lastModified;
    }

    // nio

    public static ZonedDateTime getCreationDate(Path path)
            throws IOException {
        return getCreationDate(path, ZoneId.systemDefault());
    }

    public static ZonedDateTime getCreationDate(Path path, ZoneId zoneId)
            throws IOException {
        BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
        FileTime creationTime = attributes.creationTime();
        return toZonedDateTime(creationTime, zoneId);
    }

    //

    public static ZonedDateTime getLastModified(Path path)
            throws IOException {
        return getLastModified(path, ZoneId.systemDefault());
    }

    public static ZonedDateTime getLastModified(Path path, ZoneId zoneId)
            throws IOException {
        BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
        FileTime lastModifiedTime = attributes.lastModifiedTime();
        return toZonedDateTime(lastModifiedTime, zoneId);
    }

    //

    public static ZonedDateTime getLastAccess(Path path)
            throws IOException {
        return getLastAccess(path, ZoneId.systemDefault());
    }

    public static ZonedDateTime getLastAccess(Path path, ZoneId zoneId)
            throws IOException {
        BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
        FileTime lastAccessTime = attributes.lastAccessTime();
        return toZonedDateTime(lastAccessTime, zoneId);
    }

    // utils

    public static ZonedDateTime toZonedDateTime(long epochMilli) {
        return toZonedDateTime(epochMilli, ZoneId.systemDefault());
    }

    public static ZonedDateTime toZonedDateTime(long epochMilli, ZoneId zoneId) {
        Instant instant = Instant.ofEpochMilli(epochMilli);
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zoneId);
        return zdt;
    }

    public static ZonedDateTime toZonedDateTime(FileTime time) {
        return toZonedDateTime(time, ZoneId.systemDefault());
    }

    public static ZonedDateTime toZonedDateTime(FileTime time, ZoneId zoneId) {
        Instant instant = time.toInstant();
        ZonedDateTime creationDate = ZonedDateTime.ofInstant(instant, zoneId);
        return creationDate;
    }

}
