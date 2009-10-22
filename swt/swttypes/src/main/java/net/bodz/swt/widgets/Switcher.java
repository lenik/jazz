package net.bodz.swt.widgets;

import net.bodz.swt.widgets.util.EmptyComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * @test {@link SwitcherTest}
 */
public class Switcher extends StackComposite {

    private Composite visiblePart;
    private Composite hiddenPart;
    private Composite autoLayout;

    public Switcher(Composite parent, int style) {
        this(parent, style, null);
    }

    public Switcher(Composite parent, int style, Composite autoLayout) {
        super(parent, style);
        visiblePart = new Composite(this, style);
        visiblePart.setLayout(new FillLayout());
        hiddenPart = new EmptyComposite(this, SWT.NONE);
        if (autoLayout == null)
            autoLayout = parent;
        this.autoLayout = autoLayout;
    }

    public Composite get(boolean visible) {
        return visible ? visiblePart : hiddenPart;
    }

    public void set(boolean visible) {
        if (visible)
            bringFront(this.visiblePart);
        else
            bringFront(this.hiddenPart);
        autoLayout.layout();
    }

}
