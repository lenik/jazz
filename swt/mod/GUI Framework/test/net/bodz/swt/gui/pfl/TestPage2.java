package net.bodz.swt.gui.pfl;

import net.bodz.swt.widgets.util.TestComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

public class TestPage2 extends _Page {

    TestComposite inner;

    @Override
    protected void createContents(Composite holder) {
        holder.setLayout(new FillLayout());
        inner = new TestComposite(holder, SWT.NONE);
    }

}
