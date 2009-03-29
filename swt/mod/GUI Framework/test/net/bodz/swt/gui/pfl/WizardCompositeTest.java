package net.bodz.swt.gui.pfl;

import net.bodz.swt.gui.util.SWTTest;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class WizardCompositeTest {

    public static void main(String[] args) {
        SWTTest test = new SWTTest();

        final WizardComposite wizard = new WizardComposite(test.parent,
                SWT.NONE) {
            @Override
            protected boolean isPageLoadable(String address) {
                if (super.isPageLoadable(address))
                    return true;
                if ("aaa".equals(address))
                    return true;
                return false;
            }

            @Override
            protected PageComposite loadPage(String address) {
                PageComposite page = super.loadPage(address);
                if (page == null) {
                    if ("aaa".equals(address))
                        page = new TestPage1(getContents(), SWT.NONE);
                }
                return page;
            }
        };
        wizard.definePage("bbb", new PageFactory() {
            @Override
            public PageComposite create(Composite parent) {
                return new TestPage2(parent, SWT.NONE);
            }
        });
        SymlinkPageFlow pageFlow = wizard.getPageFlow();
        pageFlow.putLink("aaa/next", "bbb");
        pageFlow.set("aaa");
        test.run();
    }

}
