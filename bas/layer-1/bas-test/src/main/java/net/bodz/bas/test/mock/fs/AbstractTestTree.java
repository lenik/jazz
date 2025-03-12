package net.bodz.bas.test.mock.fs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import net.bodz.bas.c.java.io.TempFile;

public abstract class AbstractTestTree
        implements ITestTree {

    private File preferredRoot;
    private File root;
    private String rootPath;

    public AbstractTestTree() {
        this(null);
    }

    public AbstractTestTree(File preferredRoot) {
        this.preferredRoot = preferredRoot;
    }

    public synchronized void setUp()
            throws Exception {
        if (this.root == null) {
            if (preferredRoot != null)
                this.root = preferredRoot;
            else
                this.root = TempFile.createTempDirectory("bas-test", "root");
            this.rootPath = this.root.getAbsolutePath();
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
    public File getFile(String path, File fallback) {
        File file = new File(root, path);
        String absolutePath = file.getAbsolutePath();
        if (absolutePath.startsWith(rootPath))
            return file;
        return fallback;
    }

    public File getFile(String path) {
        return getFile(path, root);
    }

    void createParents(File f) {
        File parent = f.getParentFile();
        parent.mkdirs();
    }

    /**
     * @throws FileNotFoundException
     *             If <code>path</code> is out of the root.
     */
    public void addFile(String path, byte[] blob)
            throws IOException {
        File file = getFile(path, null);
        if (file == null)
            throw new FileNotFoundException("Path out of the root: " + path);
        createParents(file);
        OutputStream out = new FileOutputStream(file);
        try {
            out.write(blob);
        } finally {
            out.close();
        }
    }

    /**
     * @throws FileNotFoundException
     *             If <code>path</code> is out of the root.
     */
    public void addFile(String path, String clob)
            throws IOException {
        File file = getFile(path, null);
        if (file == null)
            throw new FileNotFoundException("Path out of the root: " + path);
        createParents(file);
        Writer writer = new FileWriter(file);
        try {
            writer.write(clob);
        } finally {
            writer.close();
        }
    }

    /**
     * Remove a file or directory of given path. If the <code>path</code> is out of the root dir,
     * it's simply ignored.
     */
    public void removeFile(String path)
            throws IOException {
        File file = getFile(path, null);
        if (file != null)
            file.delete();
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
