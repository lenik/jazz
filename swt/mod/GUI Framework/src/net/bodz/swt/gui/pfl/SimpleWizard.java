package net.bodz.swt.gui.pfl;

import org.eclipse.swt.widgets.Composite;

public class SimpleWizard extends Composite {

    private PageFlowConfig pageFlow;

    public SimpleWizard(PageFlow pageFlow, Composite parent, int style) {
        super(parent, style);
        if (pageFlow == null)
            throw new NullPointerException("pageFlow");
        this.pageFlow = pageFlow;
        pageFlow.current();
    }

}
