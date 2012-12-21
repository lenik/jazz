package net.bodz.bas.c.java.io;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class FileZip {

    public static boolean containsAnyEntry(File _zipFile, String... entryNames)
            throws IOException {
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(_zipFile);
            for (String entryName : entryNames) {
                ZipEntry entry = zipFile.getEntry(entryName);
                if (entry != null)
                    return true;
            }
        } catch (ZipException e) {
        } finally {
            if (zipFile != null)
                try {
                    zipFile.close();
                } catch (IOException e) {
                    // ignoe.
                }
        }
        return false;
    }

}
