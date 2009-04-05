package net.bodz.dist.ins;

import org.eclipse.swt.widgets.Composite;

import net.bodz.swt.gui.pfl.PageComposite;

public class ChooseSchemePage extends PageComposite {

    private IProject config;

    public ChooseSchemePage(IProject config, Composite parent, int style) {
        super(parent, style);
        this.config = config;
    }

}
