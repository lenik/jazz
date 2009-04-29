package net.bodz.swt.controls.helper;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class Switcher extends StackComposite {

    private Composite visible;
    private Composite hidden;
    private Composite autoLayout;

    public Switcher(Composite parent, int style) {
        this(parent, style, null);
    }

    public Switcher(Composite parent, int style, Composite autoLayout) {
        super(parent, style);
        visible = new Composite(this, style);
        hidden = new EmptyComposite(this, SWT.NONE);
        if (autoLayout == null)
            autoLayout = parent;
        this.autoLayout = autoLayout;
    }

    public void switchTo(boolean visible) {
        if (visible)
            bringFront(this.visible);
        else
            bringFront(this.hidden);
        autoLayout.layout();
    }

    public Composite getVisibleComposite() {
        return visible;
    }

}
