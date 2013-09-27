package net.bodz.bas.vfs.util.find;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.c.java.io.PruneFileFilter;
import net.bodz.bas.c.java.util.Collections;
import net.bodz.bas.err.RuntimizedException;
import net.bodz.bas.t.iterator.Iterators;
import net.bodz.bas.t.iterator.PrefetchedIterator;
import net.bodz.bas.t.iterator.StackedIterator;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFileFilter;
import net.bodz.bas.vfs.VFSException;

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

    private List<IFileFoundListener> fileFoundListeners = new ArrayList<>();

    class Filter
            implements IFileFilter {

        @Override
        public boolean accept(IFile file) {
            if (userFilter == null)
                return true;
            if (file.getAttributes().isDirectory())
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

    class RecIter
            extends PrefetchedIterator<IFile> {

        StackedIterator<IFile> stack;
        boolean canceled;

        public RecIter() {
            Iterator<IFile> startIter = Iterators.iterate(startFiles);
            stack = new StackedIterator<IFile>(startIter);
        }

        public FileFinder getFinder() {
            return FileFinder.this;
        }

        @Override
        protected IFile fetch() {
            int depth = stack.size();
            if (!stack.hasNext())
                return end();

            IFile file = stack.next();
            boolean isDir = file.getAttributes().isDirectory();

            boolean included = true;
            if (!prune && userFilter != null && isDir)
                included = userFilter.accept(file);

            if (isDir && depth < maxDepth) {
                List<IFile> children = new ArrayList<IFile>();
                try {
                    for (IFile child : file.children(filter))
                        children.add(child);
                } catch (VFSException e) {
                    throw new RuntimizedException(e);
                }

                if (!children.isEmpty()) {
                    if (comparator != null)
                        Collections.sort(children, comparator);
                    Iterator<? extends IFile> citer = children.iterator();
                    stack.push(citer);
                }
            }

            if (included) {
                if (!fileFoundListeners.isEmpty()) {
                    FileFoundEvent event = new FileFoundEvent(this, file);
                    for (IFileFoundListener listener : fileFoundListeners) {
                        listener.fileFound(event);
                        if (canceled)
                            return end();
                        if (event.isExcluded())
                            included = false;
                    } // for listener
                }
                if (included)
                    return file;
            }
            return fetch();
        }

    } // RecIter

    /**
     * Exception may be thrown within iterating.
     */
    @Override
    public Iterator<IFile> iterator() {
        return new RecIter();
    }

    public Collection<String> list()
            throws IOException {
        List<String> list = new ArrayList<String>();
        RecIter iter = new RecIter();
        while (iter.hasNext()) {
            IFile file = iter.next();
            list.add(file.getName());
        }
        return list;
    }

    public Collection<IFile> listFiles()
            throws IOException {
        List<IFile> list = new ArrayList<IFile>();
        RecIter iter = new RecIter();
        while (iter.hasNext()) {
            IFile file = iter.next();
            list.add(file);
        }
        return list;
    }

    public void addFileFoundListener(IFileFoundListener listener) {
        if (listener == null)
            throw new NullPointerException("listener");
        fileFoundListeners.add(listener);
    }

    public void removeFileFoundListener(IFileFoundListener listener) {
        fileFoundListeners.remove(listener);
    }

}
