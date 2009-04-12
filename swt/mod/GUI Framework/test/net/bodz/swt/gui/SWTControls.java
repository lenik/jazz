package net.bodz.swt.gui;

import net.bodz.bas.gui.GUIException;
import net.bodz.swt.layouts.BorderLayout;
import net.bodz.swt.layouts.LineLayout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class SWTControls extends BasicGUI {

    @Override
    protected void createInitialView(Composite comp) throws GUIException,
            SWTException {
        comp.setLayout(new BorderLayout(0, 0));

        final Button button = new Button(comp, SWT.NONE);
        button.setLayoutData(BorderLayout.NORTH);
        button.setText("button"); //$NON-NLS-1$

        final Composite south = new Composite(comp, SWT.NONE);
        south.setLayout(new LineLayout());
        south.setLayoutData(BorderLayout.SOUTH);

        final Label pre = new Label(south, SWT.BORDER);
        pre.setText("a\nb"); //$NON-NLS-1$

        final Text textA = new Text(south, SWT.BORDER);
        textA.setText("1"); //$NON-NLS-1$
        textA.setLayoutData(LineLayout.EXPAND);
        final Text textB = new Text(south, SWT.BORDER);
        textB.setText("333"); //$NON-NLS-1$
        textB.setLayoutData(LineLayout.EXPAND | LineLayout.FILL);
        final Button buttonC = new Button(south, SWT.NONE);
        buttonC.setText("test"); //$NON-NLS-1$

        final Composite east = new Composite(comp, SWT.BORDER);
        east.setLayout(new LineLayout());
        east.setLayoutData(BorderLayout.EAST);

        final Text text1 = new Text(east, SWT.NONE);
        text1.setText("Very GOOD!"); //$NON-NLS-1$

        final Button button1 = new Button(east, SWT.NONE);
        button1.setText("B"); //$NON-NLS-1$
    }

    public static void main(String[] args) throws Throwable {
        new SWTControls().run(args);
    }

}
