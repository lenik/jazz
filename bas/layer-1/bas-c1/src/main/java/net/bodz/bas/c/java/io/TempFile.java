package net.bodz.bas.c.java.io;

import java.io.File;
import java.io.IOException;

public class TempFile {

    private static String tempPath;
    private static File tempRoot = null;
    static {
        tempPath = System.getenv("TEMP");
        if (tempPath == null) {
            tempPath = System.getenv("TMP");
            if (tempPath == null)
                tempPath = "/tmp";
        }
        tempRoot = new File(tempPath);
    }

    /**
     * @see File#createTempFile(String, String)
     * @see File#createTempFile(String, String, File)
     */
    public static File getTempRoot() {
        assert tempRoot != null;
        if (!tempRoot.exists())
            if (!tempRoot.mkdirs())
                throw new RuntimeException("Can't initialize temporary directory: " + tempRoot);
        return tempRoot;
    }

    public static File createTempFile(String prefix, String suffix)
            throws IOException {
        return File.createTempFile(prefix, suffix, tempRoot);
    }

    public static File createTempFile(String prefix, String suffix, File directory)
            throws IOException {
        return File.createTempFile(prefix, suffix, directory);
    }

    public static File createTempDirectory(String prefix, String suffix)
            throws IOException {
        return createTempDirectory(prefix, suffix, tempRoot);
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

    /**
     * Delete the file and all children files.
     */
    public static boolean deleteTree(File start) {
        assert start != null;
        if (!start.exists())
            return false;
        if (start.isFile())
            return start.delete();
        assert start.isDirectory();
        File[] children = start.listFiles();
        boolean succ = true;
        for (File child : children) {
            succ = deleteTree(child) && succ;
        }
        succ = start.delete() && succ;
        return succ;
    }

}
