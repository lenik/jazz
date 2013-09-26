package net.bodz.swt.c.pageflow;

import org.eclipse.swt.SWT;
import org.junit.Test;

import net.bodz.swt.c.test.WidgetTester;

public class NavigatorCompositeTest
        extends WidgetTester {

    @Test
    public void test1() {
        SimpleBook book = new SimpleBook();
        book.add(TestPage1.class, new TestPage1());
        book.add(TestPage2.class, new TestPage2());
        book.setFirst(TestPage1.class);

        final NavigatorComposite navigator = new NavigatorComposite(book, body, SWT.BORDER);

        navigator.getPageFlow().addBadPathListener(new IBadPathListener() {
            @Override
            public void badPath(BadPathEvent e) {
                shell.dispose();
                System.out.println("Exit: " + e.path);
            }
        });
    }

}
