package net.bodz.bas.vfs.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.c.java.io.PruneFileFilter;
import net.bodz.bas.c.java.util.Collections;
import net.bodz.bas.util.iter.Iterators;
import net.bodz.bas.util.iter.PrefetchedIterator;
import net.bodz.bas.util.iter.StackedIterator;
import net.bodz.bas.vfs.IFile;

public class FileFinder
        implements Iterable<IFile> {

    private static final int defaultMaxDepth = 256;

    public static final int FILE = 1;
    public static final int DIR = 2;
    public static final int DIR_POST = 4;

    private IFile[] startFiles;
    private IFileFilter userFilter;
    private boolean prune;
    private IFileFilter filter;

    private int maxDepth;
    private int order = FILE | DIR;
    private Comparator<IFile> comparator;

    class Filter
            implements IFileFilter {

        @Override
        public boolean accept(IFile file) {
            if (userFilter == null)
                return true;
            if (file.isTree())
                if (!prune)
                    return true;
            return userFilter.accept(file);
        }

    }

    public FileFinder(IFileFilter filter, boolean prune, int maxDepth, IFile... start) {
        this.startFiles = start;
        this.userFilter = filter;
        this.prune = prune;
        this.maxDepth = maxDepth;
        this.filter = new Filter();
    }

    public FileFinder(IFileFilter filter, boolean prune, IFile... start) {
        this(filter, false, defaultMaxDepth, start);
    }

    public FileFinder(IFileFilter filter, int maxDepth, IFile... start) {
        this(filter, filter instanceof PruneFileFilter, defaultMaxDepth, start);
    }

    public FileFinder(IFileFilter filter, IFile... start) {
        this(filter, defaultMaxDepth, start);
    }

    public FileFinder(int maxDepth, IFile... start) {
        this(null, false, maxDepth, start);
    }

    public FileFinder(IFile... start) {
        this(defaultMaxDepth, start);
    }

    public IFile[] getStartFiles() {
        return startFiles;
    }

    public void setStartFiles(IFile... startFiles) {
        this.startFiles = startFiles;
    }

    public IFileFilter getFilter() {
        return userFilter;
    }

    public void setFilter(IFileFilter filter) {
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

    public Comparator<IFile> getComparator() {
        return comparator;
    }

    public void setComparator(Comparator<IFile> comparator) {
        this.comparator = comparator;
    }

    class RecursiveIterator
            extends PrefetchedIterator<IFile> {

        StackedIterator<IFile> stack;

        public RecursiveIterator() {
            Iterator<IFile> startIter = Iterators.iterate(startFiles);
            stack = new StackedIterator<IFile>(startIter);
        }

        @Override
        protected IFile fetch() {
            int depth = stack.size();
            if (!stack.hasNext())
                return end();
            IFile file = stack.next();
            boolean included = true;
            if (!prune && userFilter != null && file.isTree())
                included = userFilter.accept(file);
            if (file.isTree() && depth < maxDepth) {
                List<? extends IFile> children = file.listChildren(filter);
                if (!children.isEmpty()) {
                    if (comparator != null)
                        Collections.sort(children, comparator);
                    Iterator<? extends IFile> citer = children.iterator();
                    stack.push(citer);
                }
            }
            if (included)
                return file;
            return fetch();
        }

    } // RecIter

    public Collection<String> list()
            throws IOException {
        List<String> list = new ArrayList<String>();
        RecursiveIterator iter = new RecursiveIterator();
        while (iter.hasNext()) {
            IFile file = iter.next();
            list.add(file.getName());
        }
        return list;
    }

    public Collection<IFile> listFiles()
            throws IOException {
        List<IFile> list = new ArrayList<IFile>();
        RecursiveIterator iter = new RecursiveIterator();
        while (iter.hasNext()) {
            IFile file = iter.next();
            list.add(file);
        }
        return list;
    }

    @Override
    public Iterator<IFile> iterator() {
        try {
            return listFiles().iterator();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
