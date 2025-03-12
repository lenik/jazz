package net.bodz.bas.c.java.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.bodz.bas.c.java.nio.file.FileFn;
import net.bodz.bas.c.java.util.stream.iterable.Stream;
import net.bodz.bas.err.ErrorHandlers;
import net.bodz.bas.err.IErrorHandler;

public class TempFile {

    private static String tempPath;
    private static Path tempRoot = null;

    static {
        tempPath = System.getenv("TEMP");
        if (tempPath == null) {
            tempPath = System.getenv("TMP");
            if (tempPath == null)
                tempPath = "/tmp";
        }
        tempRoot = Paths.get(tempPath);
    }

    /**
     * @see File#createTempFile(String, String)
     * @see File#createTempFile(String, String, File)
     */
    public static Path getTempRoot() {
        assert tempRoot != null;
        if (Files.notExists(tempRoot))
            if (!FileFn.mkdirs(tempRoot))
                throw new RuntimeException("Can't initialize temporary directory: " + tempRoot);
        return tempRoot;
    }

    public static Path createTempFile(String prefix, String suffix)
            throws IOException {
        return Files.createTempFile(tempRoot, prefix, suffix);
    }

    public static Path createTempFile(String prefix, String suffix, Path directory)
            throws IOException {
        return Files.createTempFile(directory, prefix, suffix);
    }

    public static Path createTempDirectory(String prefix, String suffix)
            throws IOException {
        return createTempDirectory(prefix, suffix, tempRoot);
    }

    public static Path createTempDirectory(String prefix, String suffix, Path directory)
            throws IOException {
        Path tempFile = Files.createTempFile(prefix, suffix);
        return _mktmpdir(tempFile);
    }

    static Path _mktmpdir(Path tmpfile)
            throws IOException {
        FileFn.delete(tmpfile);

        Path parent = tmpfile.getParent();
        String name = tmpfile.getFileName().toString();
        Path tmpdir = parent.resolve(name + ".d");

        int index = 0;
        while (Files.exists(tmpdir))
            tmpdir = parent.resolve(name + index++ + ".d");

        if (!FileFn.mkdir(tmpdir))
            throw new IOException("Failed to mkdir " + tmpdir);

        FileFn.deleteOnExit(tmpdir);

        return tmpdir;
    }

    // Shortcuts

    public static Path createTempFile()
            throws IOException {
        return createTempFile("bas", null);
    }

    public static Path createTempDirectory()
            throws IOException {
        return createTempDirectory("bas", null);
    }

    /**
     * Delete the file and all children files.
     */
    public static int deleteTree(Path start) {
        return deleteTree(start, ErrorHandlers.<Throwable, Path>pass());
    }

    public static int deleteTree(Path start, IErrorHandler<? super IOException, Path> handler) {
        assert start != null;
        if (Files.notExists(start))
            return 1;

        if (!Files.isDirectory(start))
            return FileFn.delete(start) ? 0 : 1;

        int errorCount = 0;
        try (Stream<Path> stream = FileFn.list(start)) {
            for (Path child : stream) {
                errorCount += deleteTree(child, handler);
            }
        } catch (IOException e) {
            handler.handleError(e, start);
            return ++errorCount;
        }

        try {
            Files.delete(start);
        } catch (IOException e) {
            handler.handleError(e, start);
            ++errorCount;
        }
        return errorCount;
    }

}
