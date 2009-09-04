package net.bodz.swt.gui.pfl;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.types.SimpleRequest;
import net.bodz.bas.types.TreePath;
import net.bodz.bas.types.util.Objects;
import net.bodz.swt.adapters.ControlAdapters;
import net.bodz.swt.gui.QuietHint;
import net.bodz.swt.gui.ValidateException;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

public abstract class PageFlow {

    private final PageContext            pageContext;
    private final Book                   book;
    private final History                history;

    private List<LocationChangeListener> locationChangeListeners;
    private List<BadPathListener>        badPathListeners;

    public PageFlow(PageContext pageContext) {
        if (pageContext == null)
            throw new NullPointerException("pageContext");
        this.pageContext = pageContext;
        this.book = pageContext.getBook();
        this.history = pageContext.getHistory();
        if (book == null)
            throw new NullPointerException("book");
        if (history == null)
            throw new NullPointerException("history");
    }

    public TreePath getLocation() {
        return this.history.get();
    }

    /**
     * @throws IllegalStateException
     *             if current page is sticked
     */
    public boolean jump(int delta) {
        TreePath prev = history.get();
        if (!history.jump(delta))
            return false;
        try {
            TreePath next = history.get();
            if (prev != null)
                _leave(prev, next);
            if (next != null)
                _enter(prev, next);
            showTurn(prev, next);
        } catch (PageException e) {
            handleQException(e);
            return false;
        }
        return true;
    }

    public boolean go(TreePath next) {
        // XXX - source?
        SimpleRequest request = new SimpleRequest(this, next);
        return submit(request);
    }

    public boolean submit(SimpleRequest request) {
        TreePath prev = history.get();
        TreePath next = request.getPath();
        if (next == null)
            throw new NullPointerException("request.path");
        try {
            if (prev != null) {
                Page page = book.getPage(prev);
                try {
                    page.validate();
                } catch (ValidateException e) {
                    handleQException(e);
                    Control control = e.getControl();
                    if (control != null)
                        control.setFocus();
                    return false;
                }
                _leave(prev, next);
            }

            TreePath referrer = prev;
            while (true) {
                if (!book.contains(next)) {
                    fireBadPath(next);
                    return false;
                }
                assert next != null;
                _enter(prev, next);
                showTurn(prev, next);

                ServiceContext context = createServiceContext(request, referrer);
                Page nextPage = book.getPage(next);
                TreePath redirect = nextPage.service(context);
                if (redirect == null)
                    break;
                referrer = next;
                next = redirect;
            }

            history.go(next);
        } catch (PageException e) {
            handleQException(e);
            return false;
        }
        return true;
    }

    protected ServiceContext createServiceContext(final SimpleRequest request,
            final TreePath referrer) {
        return new ServiceContext() {

            @Override
            public PageContext getPageContext() {
                return pageContext;
            }

            @Override
            public SimpleRequest getRequest() {
                return request;
            }

            @Override
            public TreePath getReferrerPath() {
                return referrer;
            }

        };
    }

    private void _leave(TreePath prev, TreePath next) throws PageException {
        assert prev != null;
        assert book.contains(prev);
        Page prevPage = book.getPage(prev);
        if (prevPage.isSticked())
            throw new PageException("Page is sticked: " + getPageInfo(prev, prevPage));
        prevPage.leave(next);
    }

    private void _enter(TreePath prev, TreePath next) throws PageException {
        assert next != null;
        assert book.contains(next);
        Page nextPage = book.getPage(next);
        nextPage.enter(prev);
        if (!Objects.equals(prev, next))
            fireLocationChange(prev, next);
    }

    protected abstract void showTurn(TreePath prev, TreePath path) throws PageException;

    protected void handleException(Exception e) {
        throw new Error(e);
    }

    protected final <X extends Exception & QuietHint> void handleQException(X e) {
        if (!e.isQuiet()) {
            handleException(e);
        }
    }

    /**
     * @see ControlAdapters#commit(Control, net.bodz.swt.adapters.CommitAdapter)
     */
    protected void handleValidateException(ValidateException e) {
        handleQException(e);
        Control control = e.getControl();
        if (control != null) {
            control.setFocus();
            if (control instanceof Text) {
                Text text = (Text) control;
                int len = text.getText().length();
                text.setSelection(0, len);
            }
        }
    }

    static String getPageInfo(TreePath path, Page page) {
        String pageTitle = page.getPageTitle();
        return path + " => " + pageTitle;
    }

    public void addLocationChangeListener(LocationChangeListener l) {
        if (locationChangeListeners == null)
            locationChangeListeners = new ArrayList<LocationChangeListener>(1);
        locationChangeListeners.add(l);
    }

    public void removeLocationChangeListener(LocationChangeListener l) {
        if (locationChangeListeners != null)
            locationChangeListeners.remove(l);
    }

    protected void fireLocationChange(TreePath prev, TreePath next) {
        if (Objects.equals(prev, next))
            return;
        if (locationChangeListeners != null) {
            // source??
            LocationChangeEvent e = new LocationChangeEvent(this, prev, next);
            for (LocationChangeListener l : locationChangeListeners)
                l.locationChange(e);
        }
    }

    public void addBadPathListener(BadPathListener l) {
        if (badPathListeners == null)
            badPathListeners = new ArrayList<BadPathListener>(1);
        badPathListeners.add(l);
    }

    public void removeBadPathListener(BadPathListener l) {
        if (badPathListeners != null)
            badPathListeners.remove(l);
    }

    protected void fireBadPath(TreePath path) {
        if (badPathListeners != null) {
            BadPathEvent e = new BadPathEvent(pageContext, path);
            for (BadPathListener l : badPathListeners) {
                l.badPath(e);
            }
        }
    }

}
