package net.bodz.geom.util.gd;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class DesignerMain2f {

    protected Shell             shell;
    protected DesignerCanvas2f  designer;

    protected DesignerToolbox2f toolbox;

    public static void main(String[] args) {
        try {
            DesignerMain2f window = new DesignerMain2f();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void open() {
        final Display display = Display.getDefault();
        createContents();
        shell.open();
        shell.layout();
        toolbox.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
    }

    protected void createContents() {
        shell = new Shell();
        shell.setSize(500, 375);
        shell.setText("GeoDesigner Main Window");

        designer = new DesignerCanvas2f(shell, SWT.BORDER);
        designer.setBounds(54, 28, 321, 232);
        // designer.setLayoutData(BorderLayout.CENTER);

        toolbox = new DesignerToolbox2f(designer.getGraph());
    }

}
