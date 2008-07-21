package net.bodz.swt.controls.helper;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class DynamicControl extends Composite {

    public DynamicControl(Composite parent, int style) {
        super(parent, style);
    }

    protected boolean isEmpty() {
        return getChildren().length == 0;
    }

    public int clear(Control except) {
        Control[] children = getChildren();
        int excepts = 0;
        for (Control child : children) {
            if (child.equals(except)) {
                excepts++;
                continue;
            }
            child.dispose();
        }
        return excepts;
    }

    public void setControl(Control control) {
        if (clear(control) == 0)
            control.setParent(this);
    }

}
