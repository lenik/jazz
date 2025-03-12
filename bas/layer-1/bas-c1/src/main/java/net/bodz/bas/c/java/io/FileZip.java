package net.bodz.bas.c.java.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class FileZip {

    public static boolean containsAnyEntry(File _zipFile, String... entryNames)
            throws IOException {
        try (ZipFile zipFile = new ZipFile(_zipFile)) {
            for (String entryName : entryNames) {
                ZipEntry entry = zipFile.getEntry(entryName);
                if (entry != null)
                    return true;
            }
            return false;
        } catch (ZipException e) {
            return false;
        }
    }

    public static boolean containsAnyEntry(Path _zipFile, String... entryNames)
            throws IOException {
        return containsAnyEntry(_zipFile.toFile(), entryNames);
    }

}
