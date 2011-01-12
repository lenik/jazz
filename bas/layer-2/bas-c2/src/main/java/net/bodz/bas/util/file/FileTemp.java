package net.bodz.bas.util.file;

import java.io.File;
import java.io.IOException;

public class FileTemp {

    static File TMPDIR;
    static {
        File t;
        String TEMP;
        if ((TEMP = System.getenv("TEMP")) != null)
            t = FilePath.canoniOf(TEMP);
        else if ((TEMP = System.getenv("TMP")) != null)
            t = FilePath.canoniOf(TEMP);
        else
            t = FilePath.canoniOf("/tmp");
        if (t.exists()) {
            if (!t.isDirectory())
                throw new RuntimeException("not a directory: " + t);
        } else
            t.mkdirs();
        TMPDIR = t;
    }

    /**
     * @see File#createTempFile(String, String)
     * @see File#createTempFile(String, String, File)
     */
    public static File getTmpDir() {
        return TMPDIR;
    }

    public static File createTempFile(String prefix, String suffix)
            throws IOException {
        return File.createTempFile(prefix, suffix, TMPDIR);
    }

    public static File createTempFile(String prefix, String suffix, File directory)
            throws IOException {
        return File.createTempFile(prefix, suffix, directory);
    }

    public static File createTempDirectory(String prefix, String suffix)
            throws IOException {
        return createTempDirectory(prefix, suffix, TMPDIR);
    }

    public static File createTempDirectory(String prefix, String suffix, File directory)
            throws IOException {
        File tempFile = File.createTempFile(prefix, suffix);
        return _mktmpdir(tempFile);
    }

    static File _mktmpdir(File tmpfile)
            throws IOException {
        tmpfile.delete();

        File parent = tmpfile.getParentFile();
        String name = tmpfile.getName();
        File tmpdir = new File(parent, name + ".d");

        int index = 0;
        while (tmpdir.exists())
            tmpdir = new File(parent, name + index++ + ".d");

        if (!tmpdir.mkdir())
            throw new IOException("Failed to mkdir " + tmpdir);

        tmpdir.deleteOnExit();

        return tmpdir;
    }

    // Shortcuts

    public static File createTempFile()
            throws IOException {
        return createTempFile("bas", null);
    }

    public static File createTempDirectory()
            throws IOException {
        return createTempDirectory("bas", null);
    }

}
