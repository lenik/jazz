package net.bodz.swt.draw.app.tools;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;

import net.bodz.bas.gui.dev.IDrawContext2d;
import net.bodz.swt.c.canvas.Canvas;
import net.bodz.swt.c.canvas.ICanvasMode;
import net.bodz.swt.draw.app.SubCanvasMode;

public class Hover
        extends SubCanvasMode {

    private static final long serialVersionUID = 1L;

    public Hover(Canvas canvas, ICanvasMode parent) {
        super(canvas, parent);
    }

    public void paintState(IDrawContext2d dt) {
        // assert selected != null;
        // GC gc;
        // gc.setForeground()
    }

    @Override
    public void mouseDown(MouseEvent e) {
        // Move? Scale? Rotate? ...
    }

    @Override
    public void mouseMove(MouseEvent e, MouseEvent d) {
    }

    @Override
    public void mouseUp(MouseEvent e, MouseEvent d) {
    }

    @Override
    public void mouseDoubleClick(MouseEvent e) {
        EditMajor editMajor = new EditMajor(getCanvas(), this);
        getCanvas().setMode(editMajor);
    }

    @Override
    public void keyDown(KeyEvent e) {
        ICanvasMode sub = null;
        switch (e.keyCode) {
        case java.awt.event.KeyEvent.VK_ENTER:
            // Edit outline
            sub = new EditMajor(getCanvas(), this);
            break;

        case java.awt.event.KeyEvent.VK_ESCAPE:
            // Deselect
            sub = new Select(getCanvas(), this);
            break;

        case java.awt.event.KeyEvent.VK_DELETE:
            // Delete
            sub = new Select(getCanvas(), this);
            break;
        }

        if (sub != null)
            getCanvas().setMode(sub);
    }

}
