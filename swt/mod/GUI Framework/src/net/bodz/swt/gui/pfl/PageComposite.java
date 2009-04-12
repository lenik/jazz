package net.bodz.swt.gui.pfl;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.a.A_bas;
import net.bodz.swt.gui.ValidateException;
import net.bodz.swt.gui.a.A_gui;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;

public class PageComposite extends Composite implements Page {

    private Object                        exitState = getInitialState();
    private List<PageStateChangeListener> stateChangeListeners;

    /**
     * The exit state is initialized to <code>null</code> in default
     * implementation.
     */
    public PageComposite(Composite parent, int style) {
        super(parent, style);
    }

    @Override
    public String getPageTitle() {
        Class<?> clazz = getClass();
        String doc = A_bas.getDoc(clazz);
        if (doc != null)
            return doc;
        return clazz.getName();
    }

    @Override
    public ImageData getPageIcon() {
        Class<?> clazz = getClass();
        ImageData icon = A_gui.getIcon(clazz);
        return icon;
    }

    /**
     * Do nothing in default implementation.
     */
    @Override
    public void enter(String prev) {
    }

    /**
     * Do nothing in default implementation..
     */
    @Override
    public void leave(String next) {
    }

    @Override
    public boolean isLocked() {
        return false;
    }

    /**
     * Do nothing in default implementation.
     */
    @Override
    public void validate() throws ValidateException {
    }

    protected Object getInitialState() {
        return "next"; //$NON-NLS-1$
    }

    @Override
    public Object exitState() {
        return exitState;
    }

    /**
     * the state is changed before invoke any listener.
     */
    protected void setExitState(Object state) {
        if (state == exitState)
            return;
        Object lastState = exitState;
        exitState = state;
        if (stateChangeListeners != null) {
            PageStateChangeEvent e = new PageStateChangeEvent(this, lastState,
                    state);
            for (PageStateChangeListener l : stateChangeListeners)
                l.pageStateChange(e);
        }
    }

    @Override
    public synchronized void addPageStateChangeListener(
            PageStateChangeListener listener) {
        if (stateChangeListeners == null)
            stateChangeListeners = new ArrayList<PageStateChangeListener>(1);
        stateChangeListeners.add(listener);
    }

    @Override
    public synchronized void removePageStateChangeListener(
            PageStateChangeListener listener) {
        if (stateChangeListeners == null)
            return;
        stateChangeListeners.remove(listener);
    }

    protected class SetState extends SelectionAdapter {

        private final Object state;

        public SetState(Object state) {
            this.state = state;
        }

        @Override
        public void widgetSelected(SelectionEvent e) {
            setExitState(state);
        }

    }

}
