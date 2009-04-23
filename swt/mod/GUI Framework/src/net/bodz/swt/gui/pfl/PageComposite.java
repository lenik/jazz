package net.bodz.swt.gui.pfl;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.a.A_bas;
import net.bodz.bas.types.util.Objects;
import net.bodz.bas.util.StateChangeEvent;
import net.bodz.bas.util.StateChangeListener;
import net.bodz.swt.gui.ValidateException;
import net.bodz.swt.gui.a.A_gui;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;

public class PageComposite extends Composite implements Page {

    private Object                    exitState = getInitialState();
    private List<StateChangeListener> stateChangeListeners;

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
    public void enter(String prev, int reason) {
    }

    /**
     * Do nothing in default implementation..
     */
    @Override
    public void leave(String next, int reason) {
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
    protected void setExitState(Object newExitState) {
        if (Objects.equals(exitState, newExitState))
            return;
        exitState = newExitState;
        firePageStateChange();
    }

    @Override
    public synchronized void addStateChangeListener(StateChangeListener listener) {
        if (stateChangeListeners == null)
            stateChangeListeners = new ArrayList<StateChangeListener>(1);
        stateChangeListeners.add(listener);
    }

    @Override
    public synchronized void removeStateChangeListener(StateChangeListener listener) {
        if (stateChangeListeners == null)
            return;
        stateChangeListeners.remove(listener);
    }

    protected final void firePageStateChange() {
        if (stateChangeListeners != null) {
            StateChangeEvent event = new StateChangeEvent(this);
            for (StateChangeListener listener : stateChangeListeners)
                listener.stateChange(event);
        }
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
