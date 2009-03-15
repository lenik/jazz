package net.bodz.swt.gui.pfl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.bodz.bas.lang.err.OutOfDomainException;

public class PageFlow {

    private PageFlowConfig           config;
    private String                   current;

    private LinkedList<String>       history;
    private int                      maxHistory;

    private List<PageChangeListener> pageChangeListeners;

    public PageFlow(PageFlowConfig config, String start) {
        this(config, start, 100);
    }

    public PageFlow(PageFlowConfig config, String start, int maxHistory) {
        if (config == null)
            throw new NullPointerException("config");
        this.config = config;
        this.current = start;
        if (maxHistory <= 0)
            throw new OutOfDomainException("maxHistory", maxHistory, 0);
        this.maxHistory = maxHistory;
        this.history = new LinkedList<String>();
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

    public boolean hasNext() {
        if (current == null)
            return false;
        Page currentPage = config.getPage(current);
        String nextRel = currentPage.leave();
        Page nextPage = config.getNextPage(current, nextRel);
        if (nextPage == null)
            return false;
        return true;
    }

    public String current() {
        return current;
    }

    public synchronized Page go(String relative) {
        String nextPath;
        if (current == null)
            nextPath = relative;
        else
            nextPath = config.join(current, relative);

        Page currentPage = getCurrentPage();
        Page nextPage = config.getPage(nextPath);
        if (currentPage == nextPage)
            return currentPage;

        nextPage.enter(currentPage);
        String previous = current;
        current = nextPath;

        if (history.size() > maxHistory)
            history.removeFirst();
        history.addLast(previous);

        if (pageChangeListeners != null) {
            PageChangeEvent pce = new PageChangeEvent(this, previous, nextPath);
            for (PageChangeListener pcl : pageChangeListeners)
                pcl.pageChange(pce);
        }
        return nextPage;
    }

    public Page next() {
        Page currentPage = config.getPage(current);
        if (currentPage == null)
            return null;
        String relative = currentPage.leave();
        return go(relative);
    }

}
