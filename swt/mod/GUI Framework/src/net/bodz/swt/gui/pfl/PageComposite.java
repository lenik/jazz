package net.bodz.swt.gui.pfl;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.a.A_bas;
import net.bodz.swt.gui.ValidateException;
import net.bodz.swt.gui.a.A_gui;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

public class PageComposite extends Composite implements Page {

    private List<StateChangeListener> stateChangeListeners;
    private Object                    exitState;

    /**
     * The exit state is initialized to <code>null</code> in default
     * implementation.
     */
    public PageComposite(Composite parent, int style) {
        super(parent, style);
    }

    @Override
    public String getTitle() {
        Class<?> clazz = getClass();
        String doc = A_bas.getDoc(clazz);
        if (doc != null)
            return doc;
        return clazz.getName();
    }

    @Override
    public Image getImage() {
        Class<?> clazz = getClass();
        Image icon = A_gui.getIcon(clazz);
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

    /**
     * Do nothing in default implementation.
     */
    @Override
    public void validate() throws ValidateException {
    }

    @Override
    public Object exitState() {
        return exitState;
    }

    /**
     * if any listener throws exception, the state will not be changed.
     */
    protected void setExitState(Object newExitState) {
        if (newExitState == exitState)
            return;
        if (stateChangeListeners != null) {
            StateChangeEvent e = new StateChangeEvent(this, exitState,
                    newExitState);
            for (StateChangeListener l : stateChangeListeners)
                l.stateChange(e);
        }
        exitState = newExitState;
    }

    @Override
    public synchronized void addExitStateChangeListener(
            StateChangeListener listener) {
        if (stateChangeListeners == null)
            stateChangeListeners = new ArrayList<StateChangeListener>(1);
        stateChangeListeners.add(listener);
    }

    @Override
    public synchronized void removeExitStateChangeListener(
            StateChangeListener listener) {
        if (stateChangeListeners == null)
            return;
        stateChangeListeners.remove(listener);
    }

}
