package net.bodz.swt.gui.pfl;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import net.bodz.bas.types.TreePath;
import net.bodz.bas.types.util.Objects;
import net.bodz.swt.controls.helper.EmptyComposite;
import net.bodz.swt.controls.helper.StackComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class PageContainer extends StackComposite {

    private Book                   book;
    private Composite              nonpage;
    private Map<TreePath, Control> pageMap;

    private TreePath               activePath;
    private Page                   activePage;

    public PageContainer(Book book, Composite parent, int style) {
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

    public void go(TreePath path) throws PageException {
        if (Objects.equals(activePath, path))
            return;

        Page page = null;
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

    public Page getActivePage() {
        return activePage;
    }

}
