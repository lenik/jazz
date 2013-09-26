package net.bodz.swt.c.layout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.junit.Test;

import net.bodz.swt.c.test.WidgetTester;

public class LineLayoutTest
        extends WidgetTester {

    @Test
    public void test1() {
        body.setLayout(new BorderLayout(0, 0));

        final Button button = new Button(body, SWT.NONE);
        button.setLayoutData(BorderLayout.NORTH);
        button.setText("button");

        final Composite south = new Composite(body, SWT.NONE);
        south.setLayout(new LineLayout());
        south.setLayoutData(BorderLayout.SOUTH);

        final Label pre = new Label(south, SWT.BORDER);
        pre.setText("a\nb");

        final Text textA = new Text(south, SWT.BORDER);
        textA.setText("1");
        textA.setLayoutData(LineLayout.EXPAND);
        final Text textB = new Text(south, SWT.BORDER);
        textB.setText("333");
        textB.setLayoutData(LineLayout.EXPAND | LineLayout.FILL);
        final Button buttonC = new Button(south, SWT.NONE);
        buttonC.setText("test");

        final Composite east = new Composite(body, SWT.BORDER);
        east.setLayout(new LineLayout());
        east.setLayoutData(BorderLayout.EAST);

        final Text text1 = new Text(east, SWT.NONE);
        text1.setText("Very GOOD!");

        final Button button1 = new Button(east, SWT.NONE);
        button1.setText("B");
    }

}
