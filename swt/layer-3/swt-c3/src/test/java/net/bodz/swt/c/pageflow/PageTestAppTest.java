package net.bodz.swt.c.pageflow;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import net.bodz.swt.test.PageTester;

public class PageTestAppTest {

    public static void main(String[] args) {
        PageTester app = new PageTester(new AbstractPage() {

            @Override
            public String getPageTitle() {
                return "Test Page Title";
            }

            @Override
            protected void createContents(Composite holder)
                    throws PageException {
                holder.setLayout(new FillLayout());
                Label label = new Label(holder, SWT.NONE);
                label.setText("Test Page");
            }

        });
        app.run();
    }

}
