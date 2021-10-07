package net.bodz.bas.pdf.viz;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.java.io.FileTree;
import net.bodz.bas.c.java.io.TempFile;
import net.bodz.bas.c.java.nio.TreeDeleteOption;

public class WorkDirManager
        implements
            AutoCloseable {

    int maxPartition = 10;
    Map<Integer, File> reuseDirs = new HashMap<>();

    public synchronized File getWorkDir(Integer partition)
            throws IOException {
        if (partition != null) {
            if (partition < 0 || partition >= maxPartition)
                throw new IndexOutOfBoundsException("Invalid partition: " + partition);
            File dir = reuseDirs.get(partition);
            if (dir == null) {
                dir = createWorkDir();
                reuseDirs.put(partition, dir);
            }
            return dir;
        } else {
            return createWorkDir();
        }
    }

    protected File createWorkDir()
            throws IOException {
        return TempFile.createTempDirectory();
    }

    @Override
    public void close()
            throws Exception {
        for (File workDir : reuseDirs.values()) {
            clean(workDir);
        }
    }

    protected void clean(File workDir) {
        // workDir.delete();
        FileTree.delete(workDir, TreeDeleteOption.DELETE_TREE);
    }

}
