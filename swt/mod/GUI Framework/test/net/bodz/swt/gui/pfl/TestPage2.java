package net.bodz.swt.gui.pfl;

import net.bodz.swt.controls.helper.TestComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

public class TestPage2 extends PageComposite {

    TestComposite inner;

    public TestPage2(Composite parent, int style) {
        super(parent, style);
        setLayout(new FillLayout());
        inner = new TestComposite(this, SWT.NONE);
    }

}
