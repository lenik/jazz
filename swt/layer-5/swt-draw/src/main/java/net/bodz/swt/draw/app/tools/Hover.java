package net.bodz.swt.draw.app.tools;

import net.bodz.bas.gui.dev.IDrawContext2d;
import net.bodz.swt.draw.app.DesignerState;
import net.bodz.swt.draw.app.DesignerStateGraph;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;

public class Hover
        extends DesignerState {

    public Hover(DesignerStateGraph graph) {
        super(graph);
    }

    public void paintState(IDrawContext2d dt) {
        // assert selected != null;
        // GC gc;
        // gc.setForeground()
    }

    @Override
    public DesignerState onMouseDown(MouseEvent e) {
        // Move? Scale? Rotate? ...
        //
        return super.onMouseDown(e);
    }

    @Override
    public DesignerState onMouseMove(MouseEvent e, MouseEvent d) {
        return super.onMouseMove(e, d);
    }

    @Override
    public DesignerState onMouseUp(MouseEvent e, MouseEvent d) {
        return super.onMouseUp(e, d);
    }

    @Override
    public DesignerState onMouseDoubleClick(MouseEvent e) {
        return getGraph().get(EditMajor.class);
    }

    @Override
    public DesignerState onKeyPressed(KeyEvent e) {
        switch (e.keyCode) {
        case java.awt.event.KeyEvent.VK_ENTER:
            // Edit outline
            return getGraph().get(EditMajor.class);

        case java.awt.event.KeyEvent.VK_ESCAPE:
            // Deselect
            return getGraph().get(Select.class);

        case java.awt.event.KeyEvent.VK_DELETE:
            // Delete
            return getGraph().get(Select.class);
        }

        return super.onKeyPressed(e);
    }

    private static final long serialVersionUID = -2407464748332203791L;

}
