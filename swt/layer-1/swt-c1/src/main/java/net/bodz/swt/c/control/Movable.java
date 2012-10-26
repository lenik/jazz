package net.bodz.swt.c.control;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;

public class Movable
        extends MouseKeyAdapter {

    boolean active;
    Rectangle oldBounds;
    int y0;

    /**
     * The x,y in event object represent relative-coordinates.
     */
    boolean relative;

    @Override
    protected void _mouseDown(MouseEvent e) {
        if (e.button != 1)
            return;
        if (!(e.widget instanceof Control))
            return;
        Control control = (Control) e.widget;
        oldBounds = control.getBounds();
        active = true;
    }

    @Override
    public void mouseUp(MouseEvent e, MouseEvent d) {
        active = false;
    }

    @Override
    public void mouseMove(MouseEvent e, MouseEvent d) {
        // System.out.println(e);
        // if (e.button != 1) return;
        if ((e.stateMask & SWT.BUTTON1) == 0)
            return;
        if (!active)
            return;

        assert d.widget instanceof Control;
        Control control = (Control) d.widget;

        Rectangle bounds = control.getBounds();
        int dx = e.x - d.x;
        int dy = e.y - d.y;
        bounds.x += dx;
        bounds.y += dy;
        control.setBounds(bounds);

        postMove(dx, dy);
    }

    protected void postMove(int dx, int dy) {
    }

    @Override
    public void keyDown(KeyEvent e) {
        if (!active)
            return;

        boolean cancel = false;

        switch (e.keyCode) {
        case SWT.ESC: // XXX
            cancel = true;
            active = false;
        }

        if (cancel) {
            MouseEvent lastMouseDownEvent = getLastMouseDownEvent();

            assert lastMouseDownEvent.widget instanceof Control;
            Control control = (Control) lastMouseDownEvent.widget;

            control.setBounds(oldBounds);
        }
    }

}
