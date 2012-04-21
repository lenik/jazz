package net.bodz.swt.widgets;

import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class StackComposite
        extends Composite {

    private StackLayout stackLayout;

    public StackComposite(Composite parent, int style) {
        super(parent, style);
        stackLayout = new StackLayout();
        setLayout(stackLayout);
    }

    @Override
    public Point computeSize(int wHint, int hHint, boolean changed) {
        Point size;
        if (stackLayout.topControl != null)
            size = stackLayout.topControl.computeSize(wHint, hHint, changed);
        else {
            // super.computeSize(wHint, hHint, changed); == Point(0,0)
            size = new Point(1, 1);
        }
        return size;
    }

    public void bringFront(Control control) {
        stackLayout.topControl = control;
        layout();
    }

    public int size() {
        return getChildren().length;
    }

    public boolean remove(Control control) {
        if (control == null)
            throw new NullPointerException("control");
        for (Control c : getChildren()) {
            if (control.equals(c)) {
                control.dispose();
                return true;
            }
        }
        return false;
    }

    public void clear() {
        for (Control c : getChildren())
            c.dispose();
    }

}
