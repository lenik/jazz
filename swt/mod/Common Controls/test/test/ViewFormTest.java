package test;

import net.bodz.swt.gui.util.ControlTestApp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ProgressBar;

public class ViewFormTest {

    static ViewForm viewForm;

    public static void main(String[] args) {
        ControlTestApp app = new ControlTestApp();
        viewForm = new ViewForm(app.holder, SWT.NONE);
        viewForm.setBorderVisible(true);

        ProgressBar bar = new ProgressBar(viewForm, SWT.INDETERMINATE);
        Control[] tabList = { bar };
        viewForm.setTabList(tabList);

        app.run();
    }

}
