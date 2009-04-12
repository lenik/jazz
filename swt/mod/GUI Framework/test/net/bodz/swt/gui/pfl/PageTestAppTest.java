package net.bodz.swt.gui.pfl;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class PageTestAppTest {

    public static void main(String[] args) {
        PageTestApp app = new PageTestApp() {
            @Override
            protected PageComposite createPage(Composite parent, int style) {
                PageComposite page = new PageComposite(parent, style);
                page.setLayout(new FillLayout());
                Label label = new Label(page, SWT.NONE);
                label.setText("Test Page"); //$NON-NLS-1$
                return page;
            }
        };
        app.run();
    }

}
