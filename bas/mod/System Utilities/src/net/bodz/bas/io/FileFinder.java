package net.bodz.bas.io;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.types.util.PrefetchedIterator;
import net.bodz.bas.types.util.StackedIterator;

public class FileFinder implements FileFilter, Iterable<File> {

    private static final int MAX_DEPTH = 256;

    private File             start;
    private FileFilter       filter;
    private boolean          filterDirectories;
    private int              maxDepth;
    private boolean          rootLast;
    private Comparator<File> comparator;

    public FileFinder(File start, FileFilter filter, boolean filterDirectories,
            int maxDepth, boolean rootLast, Comparator<File> comparator) {
        this.start = start;
        this.filter = filter;
        this.filterDirectories = filterDirectories;
        this.maxDepth = maxDepth;
        this.rootLast = rootLast;
        this.comparator = comparator;
    }

    public FileFinder(File start, FileFilter filter, int maxDepth) {
        this(start, filter, false, maxDepth, false, null);
    }

    public FileFinder(File start, FileFilter filter) {
        this(start, filter, MAX_DEPTH);
    }

    public FileFinder(File start, int maxDepth) {
        this(start, null, maxDepth);
    }

    public FileFinder(File start) {
        this(start, null, MAX_DEPTH);
    }

    public File getStart() {
        return start;
    }

    public void setStart(File start) {
        this.start = start;
    }

    public FileFilter getFilter() {
        return filter;
    }

    public void setFilter(FileFilter filter) {
        this.filter = filter;
    }

    public boolean isFilterDirectories() {
        return filterDirectories;
    }

    public void setFilterDirectories(boolean filterDirectories) {
        this.filterDirectories = filterDirectories;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public boolean isRootLast() {
        return rootLast;
    }

    public void setRootLast(boolean rootLast) {
        this.rootLast = rootLast;
    }

    public Comparator<File> getComparator() {
        return comparator;
    }

    public void setComparator(Comparator<File> comparator) {
        this.comparator = comparator;
    }

    @Override
    public boolean accept(File file) {
        if (filter == null)
            return true;
        if (file.isDirectory())
            if (!filterDirectories)
                return true;
        return filter.accept(file);
    }

    class Iter extends StackedIterator<File> {

        public Iter() {
            push(new RecIter(start, 0));
        }

        class RecIter extends PrefetchedIterator<File> {

            private final int  depth;
            private final File root;
            private boolean    includeRoot;
            private File[]     children;
            private int        index;
            private int        state;

            public RecIter(File root, int depth) {
                this.root = root;
                this.depth = depth;
            }

            @Override
            protected Object fetch() {
                switch (state) {
                case 0:
                    includeRoot = true;
                    if (!filterDirectories && root.isDirectory()) {
                        if (filter != null)
                            includeRoot = filter.accept(root);
                    }
                    state++;
                    if (includeRoot && !rootLast)
                        return root;
                case 1:
                    if (root.isDirectory() && depth < maxDepth) {
                        children = root.listFiles(FileFinder.this);
                        if (comparator != null)
                            Arrays.sort(children, comparator);
                        state++;
                    } else {
                        state += 2;
                        return next();
                    }
                case 2:
                    if (index < children.length) {
                        File child = children[index++];
                        RecIter childIter = new RecIter(child, depth + 1);
                        push(childIter);
                        return Iter.this.next();
                    }
                    state++;
                case 3:
                    state++;
                    if (includeRoot && rootLast)
                        return root;
                }
                return END;
            }
        }

    }

    public void run(FileCallback callback) throws IOException {
        Iter iter = new Iter();
        while (iter.hasNext()) {
            File file = iter.next();
            callback.found(file);
        }
    }

    public Collection<File> listFiles() throws IOException {
        final List<File> list = new ArrayList<File>();
        run(new FileCallback() {
            @Override
            public void found(File file) {
                list.add(file);
            }
        });
        return list;
    }

    @Override
    public Iterator<File> iterator() {
        try {
            return listFiles().iterator();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
