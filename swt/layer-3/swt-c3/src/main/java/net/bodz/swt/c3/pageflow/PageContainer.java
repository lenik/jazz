package net.bodz.swt.c3.pageflow;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.t.pojo.PathEntries;
import net.bodz.swt.c.composite.EmptyComposite;
import net.bodz.swt.c.composite.StackComposite;

public class PageContainer
        extends StackComposite {

    private IBook book;
    private Composite nonpage;
    private Map<PathEntries, Control> pageMap;

    private PathEntries activePath;
    private IPage activePage;

    public PageContainer(IBook book, Composite parent, int style) {
        super(parent, style);
        if (book == null)
            throw new NullPointerException("book");
        this.book = book;
        this.nonpage = new EmptyComposite(parent, SWT.NONE);
        this.pageMap = new HashMap<PathEntries, Control>();
    }

    public boolean remove(PathEntries path) {
        Control pageControl = pageMap.remove(path);
        if (pageControl == null)
            return false;
        pageControl.dispose();
        return true;
    }

    public boolean remove(Control pageControl) {
        if (pageControl == null)
            throw new NullPointerException("pageControl");
        for (Map.Entry<PathEntries, Control> e : pageMap.entrySet()) {
            Control c = e.getValue();
            if (pageControl.equals(c)) {
                PathEntries path = e.getKey();
                return remove(path);
            }
        }
        return false;
    }

    public void go(PathEntries path)
            throws PageException {
        if (Nullables.equals(activePath, path))
            return;

        IPage page = null;
        if (path == null) {
            bringFront(nonpage);
        } else {
            page = book.getPage(path);
            if (page == null)
                throw new NoSuchElementException("Unknown path: " + path);

            Control pageControl = pageMap.get(path);
            if (pageControl == null) {
                pageControl = page.createUI(this);
                pageMap.put(path, pageControl);
            }

            if (pageControl != null) {
                this.bringFront(pageControl);
            } else {
                /*
                 * For non-page, show in-progress waiting page,and don't make history record.
                 * 
                 * the service() method should be called after go(), and the returned path for
                 * redirection should be executed immediately.
                 */
                this.bringFront(nonpage);
            }
        }
        activePath = path;
        activePage = page;
    }

    public PathEntries getActivePath() {
        return activePath;
    }

    public IPage getActivePage() {
        return activePage;
    }

}
