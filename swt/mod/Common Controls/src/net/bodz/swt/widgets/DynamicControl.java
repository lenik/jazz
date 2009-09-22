package net.bodz.swt.widgets;

import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class DynamicControl extends Composite {

    public DynamicControl(Composite parent, int style) {
        super(parent, style);
        this.addControlListener(new ControlListener() {

            @Override
            public void controlMoved(ControlEvent e) {
            }

            @Override
            public void controlResized(ControlEvent e) {
                Point size = getSize();
                Control control = getControl();
                if (control == null)
                    return;
                control.setSize(size);
            }

        });
    }

    protected boolean isEmpty() {
        return getChildren().length == 0;
    }

    /**
     * @param keepThis
     *            don't remove this control.
     * @return number of controls haven't been removed.
     */
    private int _clear(Control keepThis) {
        Control[] children = getChildren();
        int ignored = 0;
        for (Control child : children) {
            if (keepThis != null && child.equals(keepThis)) {
                ignored++;
                continue;
            }
            child.dispose();
        }
        return ignored;
    }

    /**
     * @return <code>true</code> if successful cleared.
     */
    private boolean clear(Control keep) {
        return _clear(keep) == 0;
    }

    public void clear() {
        clear(null);
    }

    public Control getControl() {
        Control[] children = getChildren();
        if (children.length == 0)
            return null;
        return children[0];
    }

    public void setControl(Control control) {
        if (clear(control))
            control.setParent(this);
    }

    static final Point emptySize = new Point(4, 4);

    @Override
    public Point computeSize(int wHint, int hHint) {
        Control control = getControl();
        if (control == null)
            return emptySize;
        return control.computeSize(wHint, hHint);
    }

    @Override
    public Point computeSize(int wHint, int hHint, boolean changed) {
        Control control = getControl();
        if (control == null)
            return emptySize;
        return control.computeSize(wHint, hHint, changed);
    }

}
