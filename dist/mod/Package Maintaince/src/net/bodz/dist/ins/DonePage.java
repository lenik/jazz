package net.bodz.dist.ins;

import net.bodz.swt.gui.pfl.PageComposite;

import org.eclipse.swt.widgets.Composite;

public class DonePage extends PageComposite {

    private IProject project;

    public DonePage(IProject config, Composite parent, int style) {
        super(parent, style);
        this.project = config;
    }

    @Override
    protected Object getInitialState() {
        return "quit";
    }

}
