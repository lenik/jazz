package net.bodz.dist.ins;

import net.bodz.swt.gui.pfl.PageComposite;

import org.eclipse.swt.widgets.Composite;

public class CustomPage extends PageComposite {

    private IProject config;

    public CustomPage(IProject config, Composite parent, int style) {
        super(parent, style);
        this.config = config;
    }

}
