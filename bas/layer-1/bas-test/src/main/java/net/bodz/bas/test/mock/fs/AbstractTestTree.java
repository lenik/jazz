package net.bodz.bas.test.mock.fs;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import net.bodz.bas.c.java.io.TempFile;
import net.bodz.bas.c.java.nio.file.FileFn;

public abstract class AbstractTestTree
        implements ITestTree {

    private final Path preferredRoot;
    private Path root;
    private String rootPath;

    public AbstractTestTree() {
        this(null);
    }

    public AbstractTestTree(Path preferredRoot) {
        this.preferredRoot = preferredRoot;
    }

    public synchronized void setUp()
            throws Exception {
        if (this.root == null) {
            if (preferredRoot != null)
                this.root = preferredRoot;
            else
                this.root = TempFile.createTempDirectory("bas-test", "root");
            this.rootPath = FileFn.getAbsolutePath(this.root);
        }
        populate();
    }

    public void tearDown() {
        if (root != null)
            TempFile.deleteTree(root);
    }

    /**
     * Get the corresponding
     */
    public Path getFile(String path, Path fallback) {
        Path file = root.resolve(path);
        String absolutePath = FileFn.getAbsolutePath(file);
        if (absolutePath.startsWith(rootPath))
            return file;
        return fallback;
    }

    public Path getFile(String path) {
        return getFile(path, root);
    }

    boolean createParents(Path f) {
        Path parent = f.getParent();
        return FileFn.mkdirs(parent);
    }

    /**
     * @throws FileNotFoundException
     *             If <code>path</code> is out of the root.
     */
    public void addFile(String path, byte[] blob)
            throws IOException {
        Path file = getFile(path, null);
        if (file == null)
            throw new FileNotFoundException("Path out of the root: " + path);
        createParents(file);
        try (OutputStream out = Files.newOutputStream(file)) {
            out.write(blob);
        }
    }

    /**
     * @throws FileNotFoundException
     *             If <code>path</code> is out of the root.
     */
    public void addFile(String path, String clob)
            throws IOException {
        Path file = getFile(path, null);
        if (file == null)
            throw new FileNotFoundException("Path out of the root: " + path);
        createParents(file);
        try (BufferedWriter writer = Files.newBufferedWriter(file)) {
            writer.write(clob);
        }
    }

    /**
     * Remove a file or directory of given path. If the <code>path</code> is out of the root dir,
     * it's simply ignored.
     */
    public void removeFile(String path)
            throws IOException {
        Path file = getFile(path, null);
        if (file != null)
            FileFn.delete(file);
    }

    /**
     * Populate this tree.
     * <p>
     * You may add/delete files in the tree by:
     * <ul>
     * <li>{@link #addFile(String, byte[])}
     * <li>{@link #addFile(String, String)}
     * <li>{@link #removeFile(String)}
     * </ul>
     */
    protected abstract void populate()
            throws Exception;

}
