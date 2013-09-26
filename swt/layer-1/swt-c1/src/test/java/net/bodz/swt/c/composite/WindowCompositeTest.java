package net.bodz.swt.c.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.junit.Test;

import net.bodz.swt.c.resources.SWTResources;
import net.bodz.swt.c.test.WidgetTester;

public class WindowCompositeTest
        extends WidgetTester {

    private DetailMessage detail;

    @Test
    public void test1() {

        detail = new DetailMessage(body, SWT.NONE) {
            @Override
            void debug() {
                int wHint = SWT.DEFAULT;
                int hHint = SWT.DEFAULT;
                Point size;
                System.out.println();
                // size = titleBar.computeSize(wHint, hHint);
                // System.out.println("Title bar=" + size);
                //
                // size = stack.computeSize(wHint, hHint);
                // System.out.println("Stack size=" + size);

                size = computeSize(wHint, hHint);
                System.out.println("Detail size=" + size);

                size = getParent().computeSize(wHint, hHint);
                System.out.println("Parent size=" + size);

                size = body.computeSize(wHint, hHint);
                System.out.println("Test.parent=" + size);

                size = shell.computeSize(wHint, hHint);
                System.out.println("Shell size=" + size);
            }
        };
        detail.setImage(SWTResources.getImageRes("/sun/print/resources/oneside.png"));
        detail.setText("Detail Test");

        detail.addDetailSwitchListener(new DetailSwitchListener() {
            @Override
            public void detailSwitch(DetailSwitchEvent e) {
                autoFit();
            }
        });
    }

}
