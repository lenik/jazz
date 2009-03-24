package net.bodz.swt.gui.pfl;

import net.bodz.swt.gui.util.SWTTest;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class WizardCompositeTest {

    public static void main(String[] args) {
        SWTTest test = new SWTTest();

        WizardComposite wizard = new WizardComposite(test.parent, SWT.NONE) {
            @Override
            protected PageComposite createPage(Composite parent, String path) {
                if ("aaa".equals(path))
                    return new TestPage1(parent, SWT.NONE);
                return null;
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
