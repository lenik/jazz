package net.bodz.swt.gui.pfl;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import net.bodz.bas.collection.tree.TreePath;
import net.bodz.bas.util.Nullables;
import net.bodz.swt.widgets.StackComposite;
import net.bodz.swt.widgets.util.EmptyComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class PageContainer
        extends StackComposite {

    private IBook book;
    private Composite nonpage;
    private Map<TreePath, Control> pageMap;

    private TreePath activePath;
    private IPage activePage;

    public PageContainer(IBook book, Composite parent, int style) {
        super(parent, style);
        if (book == null)
            throw new NullPointerException("book");
        this.book = book;
        this.nonpage = new EmptyComposite(parent, SWT.NONE);
        this.pageMap = new HashMap<TreePath, Control>();
    }

    public boolean remove(TreePath path) {
        Control pageControl = pageMap.remove(path);
        if (pageControl == null)
            return false;
        pageControl.dispose();
        return true;
    }

    public boolean remove(Control pageControl) {
        if (pageControl == null)
            throw new NullPointerException("pageControl");
        for (Map.Entry<TreePath, Control> e : pageMap.entrySet()) {
            Control c = e.getValue();
            if (pageControl.equals(c)) {
                TreePath path = e.getKey();
                return remove(path);
            }
        }
        return false;
    }

    public void go(TreePath path)
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

    public TreePath getActivePath() {
        return activePath;
    }

    public IPage getActivePage() {
        return activePage;
    }

}
