package net.bodz.swt.draw.app;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

import net.bodz.swt.c.canvas.ICanvasMode;
import net.bodz.swt.c.canvas.IClientCanvas;
import net.bodz.swt.c.resources.SWTResources;
import net.bodz.swt.draw.app.tools.*;

public class DesignerToolbox2f {

    protected Shell shell;
    private List list;

    IClientCanvas canvas;

    Map<String, ICanvasMode> commands;

    public DesignerToolbox2f(DesignerCanvas2f canvas) {
        assert canvas != null;
        this.canvas = canvas;

        commands = new HashMap<String, ICanvasMode>();

        commands.put("select", new Select(canvas));
        commands.put("edit", new EditMajor(canvas));
        commands.put("paint", new Select(canvas));
        commands.put("point", new DrawPoint(canvas));
        commands.put("line", new DrawLine(canvas));
        commands.put("triangle", new DrawTriangle(canvas));
        commands.put("circle 3p", new DrawCircle3P(canvas));
        commands.put("circle c,r", new DrawCircle3P(canvas));
        commands.put("rectangle", new DrawRectangle(canvas));
        commands.put("n-poly", new Select(canvas));
        commands.put("n-star", new Select(canvas));
        commands.put("polygon", new Select(canvas));

        createContents();
    }

    public void open() {
        final Display display = Display.getDefault();
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
    }

    protected void createContents() {
        shell = new Shell(SWT.DIALOG_TRIM | SWT.ON_TOP | SWT.RESIZE | SWT.TOOL);
        shell.setLayout(new FillLayout());
        shell.setSize(86, 471);
        shell.setText("Drawing");

        final SashForm sashForm = new SashForm(shell, SWT.VERTICAL);
        sashForm.setBackground(SWTResources.getColor(87, 87, 87));

        list = new List(sashForm, SWT.BORDER);
        list.setItems(commands.keySet().toArray(new String[0]));
        list.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int i = list.getSelectionIndex();
                if (i == -1)
                    return;
                String cmd = list.getItem(i);
                ICanvasMode state = commands.get(cmd);
                if (state != null) 
                    canvas.setCanvasMode(state);
                }
            });

        sashForm.setWeights(new int[] { 105 });
    }

}
