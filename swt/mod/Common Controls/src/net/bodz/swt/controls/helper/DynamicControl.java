package net.bodz.swt.controls.helper;

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
     * @param except
     *            don't remove this control.
     */
    private int clear(Control except) {
        Control[] children = getChildren();
        int excepts = 0;
        for (Control child : children) {
            if (except != null && child.equals(except)) {
                excepts++;
                continue;
            }
            child.dispose();
        }
        return excepts;
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
        if (clear(control) == 0)
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
