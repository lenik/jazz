package net.bodz.swt.gui.pfl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import net.bodz.bas.lang.err.OutOfDomainException;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TextMap.TreeTextMap;
import net.bodz.swt.gui.ValidateException;

public class PageFlow {

    /**
     * unix-style path -> page object
     */
    private TextMap<Page>            pages;

    private String                   current;
    private LinkedList<String>       history;
    private int                      maxHistory;

    private List<PageChangeListener> pageChangeListeners;

    public PageFlow() {
        this(0);
    }

    public PageFlow(int maxHistory) {
        if (maxHistory < 0)
            throw new OutOfDomainException("maxHistory", maxHistory, 0);
        if (maxHistory == 0)
            maxHistory = Integer.MAX_VALUE;
        this.pages = new TreeTextMap<Page>();
        this.history = new LinkedList<String>();
        this.maxHistory = maxHistory;
    }

    public void addPageChangeListener(PageChangeListener pageChangeListener) {
        if (pageChangeListeners == null)
            pageChangeListeners = new ArrayList<PageChangeListener>(1);
        pageChangeListeners.add(pageChangeListener);
    }

    public void removePageChangeListener(PageChangeListener pageChangeListener) {
        if (pageChangeListeners != null)
            pageChangeListeners.remove(pageChangeListener);
    }

    public Collection<Page> getPages() {
        return pages.values();
    }

    protected boolean isPageLoaded(String path) {
        return pages.containsKey(path);
    }

    public Page getPage(String path) {
        Page page = pages.get(path);
        if (page == null) {
            page = loadPage(path);
            if (page != null)
                pages.put(path, page);
        }
        return page;
    }

    /**
     * @return <code>null</code> in default implementation.
     */
    public Page loadPage(String path) {
        return null;
    }

    public String getCurrentPath() {
        return current;
    }

    /**
     * the current page is changed before trigger {@link PageChangeListener}.
     */
    public void setCurrentPath(String newPath) {
        String oldPath = current;
        current = newPath;
        if (pageChangeListeners != null) {
            PageChangeEvent e = new PageChangeEvent(this, oldPath, newPath);
            for (PageChangeListener l : pageChangeListeners)
                l.pageChange(e);
        }
    }

    public Page getCurrentPage() {
        return getPage(current);
    }

    public boolean hasPrevious() {
        return !history.isEmpty();
    }

    public Page previous() {
        if (history.isEmpty())
            throw new NoSuchElementException("previous");
        String prevPath = history.removeFirst();
        Page prevPage = getPage(prevPath);
        setCurrentPath(prevPath);
        return prevPage;
    }

    protected String getNextPath(String path, Object exitState) {
        String s;
        if (exitState == null)
            s = "next";
        else
            s = String.valueOf(exitState);
        String nextPath = PagePath.join(path, s);
        return nextPath;
    }

    public boolean hasNext() {
        Page currentPage = getCurrentPage();
        if (currentPage == null)
            return false;
        String nextPath = getNextPath(current, currentPage.exitState());
        Page nextPage = getPage(nextPath);
        return nextPage != null;
    }

    public Page next() throws ValidateException {
        Page currentPage = getCurrentPage();
        if (currentPage == null)
            throw new IllegalStateException("no current page");
        String nextPath = getNextPath(current, currentPage.exitState());
        return go(nextPath);
    }

    public synchronized Page go(String path) throws ValidateException {
        Page currentPage = getCurrentPage();
        Page nextPage = getPage(path);
        if (currentPage == nextPage)
            return currentPage;

        if (currentPage != null) {
            currentPage.validate();
            currentPage.leave(path);
        }
        if (nextPage != null)
            nextPage.enter(current);

        if (current != null) {
            if (history.size() > maxHistory)
                history.removeFirst();
            history.addLast(current);
        }

        setCurrentPath(path);
        return nextPage;
    }

}
