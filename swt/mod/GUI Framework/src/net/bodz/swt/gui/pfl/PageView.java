package net.bodz.swt.gui.pfl;

import org.eclipse.swt.widgets.Composite;

public abstract class PageView extends Composite {

    public PageView(Composite parent, int style) {
        super(parent, style);
    }

    public abstract void setBean(PageBean bean);

}
