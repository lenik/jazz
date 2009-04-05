package net.bodz.dist.ins;

import net.bodz.swt.gui.pfl.PageComposite;

import org.eclipse.swt.widgets.Composite;

public class CanceledPage extends PageComposite {

    private IProject config;

    public CanceledPage(IProject config, Composite parent, int style) {
        super(parent, style);
        this.config = config;
    }

    @Override
    protected Object getInitialState() {
        return "quit";
    }

}
