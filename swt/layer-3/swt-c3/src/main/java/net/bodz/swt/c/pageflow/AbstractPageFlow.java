package net.bodz.swt.c.pageflow;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import net.bodz.bas.c.event.IQuietHint;
import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.repr.req.MutableRequest;
import net.bodz.bas.t.pojo.PathEntries;
import net.bodz.bas.ui.err.UiValidationException;
import net.bodz.swt.c.control.ControlAdapters;

public abstract class AbstractPageFlow
        implements IPageFlow {

    private final IPageContext pageContext;
    private final IBook book;
    private final History history;

    private List<LocationChangeListener> locationChangeListeners;
    private List<IBadPathListener> badPathListeners;

    public AbstractPageFlow(IPageContext pageContext) {
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

    public PathEntries getLocation() {
        return this.history.get();
    }

    public boolean go(int pageCount) {
        PathEntries prev = history.get();
        if (!history.jump(pageCount))
            return false;
        try {
            PathEntries next = history.get();
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

    public boolean go(PathEntries next) {
        // XXX - source?
        MutableRequest request = new MutableRequest(this, next);
        return submit(request);
    }

    public boolean submit(MutableRequest request) {
        PathEntries prev = history.get();
        PathEntries next = request.getPath();
        if (next == null)
            throw new NullPointerException("request.path");
        try {
            if (prev != null) {
                IPage page = book.getPage(prev);
                try {
                    page.validate();
                } catch (UiValidationException e) {
                    handleQException(e);
                    Control control = e.getControl();
                    if (control != null)
                        control.setFocus();
                    return false;
                }
                _leave(prev, next);
            }

            PathEntries referrer = prev;
            while (true) {
                if (!book.contains(next)) {
                    fireBadPath(next);
                    return false;
                }
                assert next != null;
                _enter(prev, next);
                showTurn(prev, next);

                ServiceContext context = createServiceContext(request, referrer);
                IPage nextPage = book.getPage(next);
                PathEntries redirect = nextPage.service(context);
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

    protected ServiceContext createServiceContext(final MutableRequest request, final PathEntries referrer) {
        return new ServiceContext() {

            @Override
            public IPageContext getPageContext() {
                return pageContext;
            }

            @Override
            public MutableRequest getRequest() {
                return request;
            }

            @Override
            public PathEntries getReferrerPath() {
                return referrer;
            }

        };
    }

    private void _leave(PathEntries prev, PathEntries next)
            throws PageException {
        assert prev != null;
        assert book.contains(prev);
        IPage prevPage = book.getPage(prev);
        if (prevPage.isSticked())
            throw new PageException("Page is sticked: " + getPageInfo(prev, prevPage));
        prevPage.leave(next);
    }

    private void _enter(PathEntries prev, PathEntries next)
            throws PageException {
        assert next != null;
        assert book.contains(next);
        IPage nextPage = book.getPage(next);
        nextPage.enter(prev);
        if (!Nullables.equals(prev, next))
            fireLocationChange(prev, next);
    }

    protected abstract void showTurn(PathEntries prev, PathEntries path)
            throws PageException;

    protected void handleException(Exception e) {
        throw new Error(e);
    }

    protected final <X extends Exception & IQuietHint> void handleQException(X e) {
        if (!e.isQuiet()) {
            handleException(e);
        }
    }

    /**
     * @see ControlAdapters#autocommitForFocus(Control, net.bodz.swt.adapters.CommitAdapter)
     */
    protected void handleValidateException(UiValidationException e) {
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

    static String getPageInfo(PathEntries path, IPage page) {
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

    protected void fireLocationChange(PathEntries prev, PathEntries next) {
        if (Nullables.equals(prev, next))
            return;
        if (locationChangeListeners != null) {
            // source??
            LocationChangeEvent e = new LocationChangeEvent(this, prev, next);
            for (LocationChangeListener l : locationChangeListeners)
                l.locationChange(e);
        }
    }

    public void addBadPathListener(IBadPathListener l) {
        if (badPathListeners == null)
            badPathListeners = new ArrayList<IBadPathListener>(1);
        badPathListeners.add(l);
    }

    public void removeBadPathListener(IBadPathListener l) {
        if (badPathListeners != null)
            badPathListeners.remove(l);
    }

    protected void fireBadPath(PathEntries path) {
        if (badPathListeners != null) {
            BadPathEvent e = new BadPathEvent(pageContext, path);
            for (IBadPathListener l : badPathListeners) {
                l.badPath(e);
            }
        }
    }

}
