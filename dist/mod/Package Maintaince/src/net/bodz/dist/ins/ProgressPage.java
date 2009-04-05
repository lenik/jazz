package net.bodz.dist.ins;

import net.bodz.swt.gui.pfl.PageComposite;

import org.eclipse.swt.widgets.Composite;

public class ProgressPage extends PageComposite {

    private IProject project;
    private boolean  done;

    public ProgressPage(IProject project, Composite parent, int style) {
        super(parent, style);
        this.project = project;
    }

    @Override
    public boolean isLocked() {
        return done;
    }

}
