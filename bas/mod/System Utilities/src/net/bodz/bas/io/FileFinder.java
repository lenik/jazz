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

import net.bodz.bas.lang.err.NotImplementedException;
import net.bodz.bas.types.util.PrefetchedIterator;
import net.bodz.bas.types.util.StackedIterator;

/**
 * @test FileFinderTest
 */
public class FileFinder implements FileFilter, Iterable<File> {

    private static final int MAX_DEPTH = 256;

    private File             start;
    private FileFilter       filter;
    private boolean          filterDirectories;
    private int              maxDepth;
    private boolean          rootLast;
    private Comparator<File> order;

    public FileFinder(File start, FileFilter filter, boolean filterDirectories,
            int maxDepth, boolean rootLast, Comparator<File> order) {
        this.start = start;
        this.filter = filter;
        this.filterDirectories = filterDirectories;
        this.maxDepth = maxDepth;
        if (rootLast)
            throw new NotImplementedException(
                    "root-last feature isn't supported. ");
        this.rootLast = rootLast;
        this.order = order;
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
        return order;
    }

    public void setComparator(Comparator<File> comparator) {
        this.order = comparator;
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

    class RecIter extends StackedIterator<File> {

        public RecIter() {
            push(new TreeIter(start, 0));
        }

        class Iter extends PrefetchedIterator<File> {

            private final int    depth;
            private final File[] files;
            private int          index;

            public Iter(int depth, File... files) {
                this.depth = depth;
                this.files = files;
            }

            @Override
            protected File fetch() {
                if (index > files.length)
                    return end();
                File file = files[index++];
                if (file.isDirectory()) {
                    File[] children = file.listFiles();
                    Iter childrenIter = new Iter(depth + 1, children);
                    push(childrenIter);
                }
                return file;
            }

        }

        class ChildrenIter extends PrefetchedIterator<File> {

            private final File   parent;
            private final int    depth;
            private final File[] children;
            private int          index;

            public ChildrenIter(File parent, int depth, File... children) {
                this.depth = depth;
                this.parent = parent;
                this.children = children;
                if (rootLast)
                    pushIfChildDir();
            }

            void pushIfChildDir() {
                if (index < children.length) {
                    if (depth < MAX_DEPTH) {
                        File child = children[index];
                        TreeIter childIter = new TreeIter(child, depth + 1);
                        push(childIter);
                    }
                }
            }

            @Override
            protected File fetch() {
                if (index < children.length) {
                    File child = children[index];
                    if (rootLast) {
                        index++;
                        pushIfChildDir(); // n-1 times
                    } else {
                        pushIfChildDir(); // n times
                        index++;
                    }
                    return child;
                }
                return end();
            }

            @Override
            public String toString() {
                return "Children(" + index + ", " + parent + ")\n" + //
                        "        " + super.toString();
            }

        }

        class TreeIter extends PrefetchedIterator<File> {

            private final File   root;
            private boolean      includeRoot;
            private ChildrenIter childrenIter;
            private int          state;

            public TreeIter(File root, int depth) {
                assert root != null;
                if (root == null)
                    throw new NullPointerException("root");
                this.root = root;
                includeRoot = true;
                if (!filterDirectories && root.isDirectory()) {
                    if (filter != null)
                        includeRoot = filter.accept(root);
                }
                if (root.isDirectory() && depth < maxDepth) {
                    File[] children = root.listFiles(FileFinder.this);
                    if (children.length > 0) {
                        if (order != null)
                            Arrays.sort(children, order);
                        childrenIter = new ChildrenIter(root, depth, children);
                    }
                }
                if (rootLast && childrenIter != null)
                    push(childrenIter);
            }

            @Override
            protected File fetch() {
                switch (state) {
                case 0:
                    state++;
                    if (!rootLast && childrenIter != null)
                        push(childrenIter);
                    if (includeRoot)
                        return root;
                case 1:
                }
                return end();
            }

            @Override
            public String toString() {
                return "Root(" + state + ", " + root + ")\n" + //
                        "        " + super.toString();
            }

        }

    }

    public Collection<String> list() throws IOException {
        List<String> list = new ArrayList<String>();
        RecIter iter = new RecIter();
        while (iter.hasNext()) {
            File file = iter.next();
            list.add(file.getPath());
        }
        return list;
    }

    public Collection<File> listFiles() throws IOException {
        List<File> list = new ArrayList<File>();
        RecIter iter = new RecIter();
        while (iter.hasNext()) {
            File file = iter.next();
            list.add(file);
        }
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
