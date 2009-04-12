package net.bodz.swt.gui.pfl;

import net.bodz.swt.gui.util.ControlTestApp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class WizardCompositeTest {

    public static void main(String[] args) {
        final ControlTestApp test = new ControlTestApp();

        final WizardComposite wizard = new WizardComposite(test.parent,
                SWT.NONE) {
            @Override
            protected boolean isPageLoadable(String address) {
                if (super.isPageLoadable(address))
                    return true;
                if ("aaa".equals(address)) //$NON-NLS-1$
                    return true;
                return false;
            }

            @Override
            protected PageComposite loadPage(String address) {
                PageComposite page = super.loadPage(address);
                if (page == null) {
                    if ("aaa".equals(address)) //$NON-NLS-1$
                        page = new TestPage1(getContents(), SWT.NONE);
                }
                return page;
            }
        };
        wizard.definePage("bbb", new PageFactory() { //$NON-NLS-1$
                    @Override
                    public PageComposite create(Composite parent) {
                        return new TestPage2(parent, SWT.NONE);
                    }
                });
        wizard.addExitListener(new WizardExitListener() {
            @Override
            public void wizardExit(WizardExitEvent e) {
                test.shell.dispose();
                System.out.println("Exit: " + e.address); //$NON-NLS-1$
            }
        });
        SymlinkPageFlow pageFlow = wizard.getPageFlow();
        pageFlow.putLink("aaa/next", "bbb"); //$NON-NLS-1$ //$NON-NLS-2$
        pageFlow.set("aaa"); //$NON-NLS-1$
        test.run();
    }

}
