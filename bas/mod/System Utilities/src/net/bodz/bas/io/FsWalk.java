package net.bodz.bas.io;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public abstract class FsWalk implements FileFilter {

    private static final int MAX_DEPTH = 256;

    private File             start;
    private FileFilter       filter;
    private boolean          filterDirectories;
    private int              maxDepth;
    private boolean          rootLast;
    private Comparator<File> comparator;

    public FsWalk(File start, FileFilter filter, boolean filterDirectories,
            int maxDepth, boolean rootLast, Comparator<File> comparator) {
        this.start = start;
        this.filter = filter;
        this.filterDirectories = filterDirectories;
        this.maxDepth = maxDepth;
        this.rootLast = rootLast;
        this.comparator = comparator;
    }

    public FsWalk(File start, FileFilter filter, int maxDepth) {
        this(start, filter, false, maxDepth, false, null);
    }

    public FsWalk(File start, FileFilter filter) {
        this(start, filter, MAX_DEPTH);
    }

    public FsWalk(File start, int maxDepth) {
        this(start, null, maxDepth);
    }

    public FsWalk(File start) {
        this(start, null, MAX_DEPTH);
    }

    public abstract void process(File file) throws IOException;

    @Override
    public boolean accept(File file) {
        if (filter == null)
            return true;
        if (file.isDirectory())
            if (!filterDirectories)
                return true;
        return filter.accept(file);
    }

    public void walk() throws IOException {
        walk(start, 0);
    }

    protected void walk(File file, int depth) throws IOException {
        boolean process = true;
        if (!filterDirectories && file.isDirectory()) {
            if (filter != null)
                process = filter.accept(file);
        }
        if (process && !rootLast)
            process(file);
        if (file.isDirectory() && depth++ < maxDepth) {
            File[] list = file.listFiles(this);
            if (comparator != null)
                Arrays.sort(list, comparator);
            for (File f : list)
                walk(f, depth);
        }
        if (process && rootLast)
            process(file);
    }
}
