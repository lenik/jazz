package net.bodz.dist.ins;

import net.bodz.swt.gui.pfl.PageComposite;

import org.eclipse.swt.widgets.Composite;

public class CanceledPage extends PageComposite {

    private ISession session;

    public CanceledPage(ISession session, Composite parent, int style) {
        super(parent, style);
        this.session = session;
    }

    @Override
    protected Object getInitialState() {
        return "quit";
    }

}
