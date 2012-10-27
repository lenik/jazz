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

import net.bodz.swt.c.canvas.Canvas;
import net.bodz.swt.c.canvas.ICanvasMode;
import net.bodz.swt.c.resources.SWTResources;
import net.bodz.swt.draw.app.tools.*;

public class DesignerToolbox2f {

    protected Shell shell;
    private List list;

    Canvas canvas;

    Map<String, ICanvasMode> canvasModes;

    public DesignerToolbox2f(DesignerCanvas2f designerCanvas) {
        assert designerCanvas != null;
        this.canvas = designerCanvas.canvas;

        canvasModes = new HashMap<String, ICanvasMode>();

        Select select = new Select(canvas, null);
        canvasModes.put("select", select);
        canvasModes.put("edit", new EditMajor(canvas, select));
        canvasModes.put("paint", new Select(canvas, select));
        canvasModes.put("point", new DrawPoint(canvas, select));
        canvasModes.put("line", new DrawLine(canvas, select));
        canvasModes.put("triangle", new DrawTriangle(canvas, select));
        canvasModes.put("circle 3p", new DrawCircle3P(canvas, select));
        canvasModes.put("circle c,r", new DrawCircle3P(canvas, select));
        canvasModes.put("rectangle", new DrawRectangle(canvas, select));
        canvasModes.put("n-poly", new Select(canvas, select));
        canvasModes.put("n-star", new Select(canvas, select));
        canvasModes.put("polygon", new Select(canvas, select));

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
        list.setItems(canvasModes.keySet().toArray(new String[0]));
        list.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int i = list.getSelectionIndex();
                if (i == -1)
                    return;
                String modeName = list.getItem(i);
                ICanvasMode mode = canvasModes.get(modeName);
                if (mode != null)
                    canvas.setMode(mode);
            }
        });

        sashForm.setWeights(new int[] { 105 });
    }

}
