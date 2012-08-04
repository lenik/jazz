package net.bodz.swt.draw.app.tools;

import net.bodz.swt.draw.app.GDState;
import net.bodz.swt.draw.app.GDStateGraph;
import net.bodz.swt.draw.dev.DrawTarget2f;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;

public class Hover
        extends GDState {

    public Hover(GDStateGraph graph) {
        super(graph);
    }

    public void paintState(DrawTarget2f dt) {
        // assert selected != null;
        // GC gc;
        // gc.setForeground()
    }

    @Override
    public GDState onMouseDown(MouseEvent e) {
        // Move? Scale? Rotate? ...
        //
        return super.onMouseDown(e);
    }

    @Override
    public GDState onMouseMove(MouseEvent e, MouseEvent d) {
        return super.onMouseMove(e, d);
    }

    @Override
    public GDState onMouseUp(MouseEvent e, MouseEvent d) {
        return super.onMouseUp(e, d);
    }

    @Override
    public GDState onMouseDoubleClick(MouseEvent e) {
        return getGraph().get(EditMajor.class);
    }

    @Override
    public GDState onKeyPressed(KeyEvent e) {
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
