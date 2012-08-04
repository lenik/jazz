package net.bodz.swt.c3.pageflow;

import net.bodz.swt.c3.test.TestComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

public class TestPage2
        extends AbstractPage {

    TestComposite inner;

    @Override
    protected void createContents(Composite holder) {
        holder.setLayout(new FillLayout());
        inner = new TestComposite(holder, SWT.NONE);
    }

}
