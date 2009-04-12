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

import net.bodz.bas.types.util.Iterators;
import net.bodz.bas.types.util.PrefetchedIterator;
import net.bodz.bas.types.util.StackedIterator;

/**
 * @test FileFinderTest
 */
public class FileFinder implements FileFilter, Iterable<File> {

    private static final int defaultMaxDepth = 256;

    public static final int  FILE            = 1;
    public static final int  DIR             = 2;
    public static final int  DIR_POST        = 4;

    private File[]           start;
    private FileFilter       filter;
    private boolean          prune;
    private int              maxDepth;
    private int              order           = FILE | DIR;
    private Comparator<File> comparator;

    public FileFinder(FileFilter filter, int maxDepth, boolean prune,
            File... start) {
        this.start = start;
        this.filter = filter;
        this.maxDepth = maxDepth;
        this.prune = prune;
    }

    public FileFinder(FileFilter filter, int maxDepth, File... start) {
        this(filter, maxDepth, false, start);
    }

    public FileFinder(FileFilter filter, File... start) {
        this(filter, defaultMaxDepth, start);
    }

    public FileFinder(int maxDepth, File... start) {
        this(null, maxDepth, start);
    }

    public FileFinder(File... start) {
        this(null, defaultMaxDepth, start);
    }

    public File[] getStart() {
        return start;
    }

    public void setStart(File... start) {
        this.start = start;
    }

    public FileFilter getFilter() {
        return filter;
    }

    public void setFilter(FileFilter filter) {
        this.filter = filter;
    }

    public boolean isPrune() {
        return prune;
    }

    public void setPrune(boolean prune) {
        this.prune = prune;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        if (0 != (order & DIR_POST))
            throw new UnsupportedOperationException("DIR_POST"); //$NON-NLS-1$
        this.order = order;
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
            if (!prune)
                return true;
        return filter.accept(file);
    }

    class RecIter extends PrefetchedIterator<File> {

        StackedIterator<File> stack;

        public RecIter() {
            Iterator<File> startIter = Iterators.iterator(start);
            stack = new StackedIterator<File>(startIter);
        }

        @Override
        protected File fetch() {
            int depth = stack.size();
            if (!stack.hasNext())
                return end();
            File x = stack.next();
            boolean included = true;
            if (!prune && filter != null && x.isDirectory())
                included = filter.accept(x);
            if (x.isDirectory() && depth < maxDepth) {
                File[] children = x.listFiles(FileFinder.this);
                if (children.length > 0) {
                    if (comparator != null)
                        Arrays.sort(children, comparator);
                    Iterator<File> citer = Iterators.iterator(children);
                    stack.push(citer);
                }
            }
            if (included)
                return x;
            return fetch();
        }

    } // RecIter

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
