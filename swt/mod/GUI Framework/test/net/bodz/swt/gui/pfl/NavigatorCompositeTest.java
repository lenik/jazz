package net.bodz.swt.gui.pfl;

import net.bodz.swt.gui.util.ControlTestApp;

import org.eclipse.swt.SWT;
import org.junit.Test;

public class NavigatorCompositeTest {

    @Test
    public void test1() {
        SimpleBook book = new SimpleBook();
        book.add(TestPage1.class, new TestPage1());
        book.add(TestPage2.class, new TestPage2());
        book.setFirst(TestPage1.class);

        final ControlTestApp test = new ControlTestApp();
        final NavigatorComposite navigator = new NavigatorComposite(book, test.holder, SWT.BORDER);

        navigator.getPageFlow().addBadPathListener(new BadPathListener() {
            @Override
            public void badPath(BadPathEvent e) {
                test.shell.dispose();
                System.out.println("Exit: " + e.path); //$NON-NLS-1$
            }
        });
        test.run();
    }

}
