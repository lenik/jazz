package net.bodz.swt.draw.app;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import net.bodz.swt.program.BasicGUI;

public class DesignerMain2f
        extends BasicGUI {

    protected DesignerCanvas2f canvas;
    protected DesignerToolbox2f toolbox;

    protected void createContents() {
        shell = new Shell();
        shell.setSize(500, 375);
        shell.setText("GeoDesigner Main Window");

        canvas = new DesignerCanvas2f(shell, SWT.BORDER);
        canvas.setBounds(54, 28, 321, 232);
        // designer.setLayoutData(BorderLayout.CENTER);

        toolbox = new DesignerToolbox2f(canvas);
    }

    public static void main(String[] args)
            throws Exception {
        new DesignerMain2f().execute(args);
    }

}
