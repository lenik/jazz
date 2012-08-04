package net.bodz.swt.draw.app;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.fsm.base.IStateGraph;
import net.bodz.swt.gui.state.ISWTState;
import net.bodz.swt.resources.SWTResources;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

public class DesignerToolbox2f {

    protected Shell shell;
    private List list;

    IStateGraph graph;

    Map<String, ISWTState> commands;

    public DesignerToolbox2f(IStateGraph graph) {
        assert graph != null;
        this.graph = graph;

        commands = new HashMap<String, ISWTState>();

        /*
         * commands.put("select", new Select(graph)); commands.put("edit", new EditMajor(graph));
         * commands.put("paint", new Select(graph)); commands.put("point", new DrawPoint(graph));
         * commands.put("line", new DrawLine(graph)); commands.put("triangle", new
         * DrawTriangle(graph)); commands.put("circle 3p", new DrawCircle3P(graph));
         * commands.put("circle c,r", new DrawCircle3P(graph)); commands.put("rectangle", new
         * DrawRectangle(graph)); commands.put("n-poly", new Select(graph)); commands.put("n-star",
         * new Select(graph)); commands.put("polygon", new Select(graph));
         */

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
                ISWTState state = commands.get(cmd);
                if (state != null) {
                    graph.jump(state);
                }
            }
        });

        sashForm.setWeights(new int[] { 105 });
    }

}
