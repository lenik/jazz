package net.bodz.bas.io.fs.traverse;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.collection.iterator.ArrayIterator;
import net.bodz.bas.collection.iterator.PrefetchedIterator;
import net.bodz.bas.collection.iterator.StackedIterator;

/**
 * @test FileFinderTest
 */
public class FileFinder
        implements Iterable<File> {

    private static final int defaultMaxDepth = 256;

    public static final int FILE = 1;
    public static final int DIR = 2;
    public static final int DIR_POST = 4;

    private File[] startFiles;
    private FileFilter userFilter;
    private boolean prune;
    private FileFilter filter;

    private int maxDepth;
    private int order = FILE | DIR;
    private Comparator<File> comparator;

    class Filter
            implements FileFilter {

        @Override
        public boolean accept(File file) {
            if (userFilter == null)
                return true;
            if (file.isDirectory())
                if (!prune)
                    return true;
            return userFilter.accept(file);
        }

    }

    public FileFinder(FileFilter filter, boolean prune, int maxDepth, File... start) {
        this.startFiles = start;
        this.userFilter = filter;
        this.prune = prune;
        this.maxDepth = maxDepth;
        this.filter = new Filter();
    }

    public FileFinder(FileFilter filter, boolean prune, File... start) {
        this(filter, false, defaultMaxDepth, start);
    }

    public FileFinder(FileFilter filter, int maxDepth, File... start) {
        this(filter, filter instanceof PruneFileFilter, defaultMaxDepth, start);
    }

    public FileFinder(FileFilter filter, File... start) {
        this(filter, defaultMaxDepth, start);
    }

    public FileFinder(int maxDepth, File... start) {
        this(null, false, maxDepth, start);
    }

    public FileFinder(File... start) {
        this(defaultMaxDepth, start);
    }

    public File[] getStart() {
        return startFiles;
    }

    public void setStart(File... start) {
        this.startFiles = start;
    }

    public FileFilter getFilter() {
        return userFilter;
    }

    public void setFilter(FileFilter filter) {
        this.userFilter = filter;
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
            throw new UnsupportedOperationException("DIR_POST"); 
        this.order = order;
    }

    public Comparator<File> getComparator() {
        return comparator;
    }

    public void setComparator(Comparator<File> comparator) {
        this.comparator = comparator;
    }

    class RecIter
            extends PrefetchedIterator<File> {

        StackedIterator<File> stack;

        public RecIter() {
            Iterator<File> startIter = ArrayIterator.getInstance(startFiles);
            stack = new StackedIterator<File>(startIter);
        }

        @Override
        protected File fetch() {
            int depth = stack.size();
            if (!stack.hasNext())
                return end();
            File x = stack.next();
            boolean included = true;
            if (!prune && userFilter != null && x.isDirectory())
                included = userFilter.accept(x);
            if (x.isDirectory() && depth < maxDepth) {
                File[] children = x.listFiles(filter);
                if (children.length > 0) {
                    if (comparator != null)
                        Arrays.sort(children, comparator);
                    Iterator<File> citer = ArrayIterator.getInstance(children);
                    stack.push(citer);
                }
            }
            if (included)
                return x;
            return fetch();
        }

    } // RecIter

    public Collection<String> list()
            throws IOException {
        List<String> list = new ArrayList<String>();
        RecIter iter = new RecIter();
        while (iter.hasNext()) {
            File file = iter.next();
            list.add(file.getPath());
        }
        return list;
    }

    public Collection<File> listFiles()
            throws IOException {
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
