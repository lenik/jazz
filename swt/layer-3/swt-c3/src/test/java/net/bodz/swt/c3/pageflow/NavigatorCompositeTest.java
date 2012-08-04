package net.bodz.swt.c3.pageflow;

import net.bodz.swt.c3.test.ControlTestApp;

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

        navigator.getPageFlow().addBadPathListener(new IBadPathListener() {
            @Override
            public void badPath(BadPathEvent e) {
                test.shell.dispose();
                System.out.println("Exit: " + e.path);
            }
        });
        test.run();
    }

}
