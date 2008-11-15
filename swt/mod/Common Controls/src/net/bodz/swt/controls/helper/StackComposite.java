package net.bodz.swt.controls.helper;

import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class StackComposite extends Composite {

    private StackLayout stackLayout;

    public StackComposite(Composite parent, int style) {
        super(parent, style);
        stackLayout = new StackLayout();
        setLayout(stackLayout);
    }

    public void bringFront(Control control) {
        stackLayout.topControl = control;
        layout();
    }

    @Override
    public Point computeSize(int wHint, int hHint) {
        Point size = super.computeSize(wHint, hHint);
        if (size.x == 0)
            size.x = 1;
        if (size.y == 0)
            size.y = 1;
        return size;
    }

}
