package net.bodz.swt.c3.pageflow;

import net.bodz.swt.c3.test.PageTestApp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class PageTestAppTest {

    public static void main(String[] args) {
        PageTestApp app = new PageTestApp(new AbstractPage() {

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
