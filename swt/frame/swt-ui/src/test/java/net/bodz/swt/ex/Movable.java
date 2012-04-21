package net.bodz.swt.ex;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;

public class Movable
        extends HistoryMouseListener
        implements MouseMoveListener, KeyListener {

    boolean active;
    Rectangle oldBounds;
    int y0;

    /**
     * The x,y in event object represent relative-coordinates.
     */
    boolean relative;

    @Override
    protected void mouseDown2(MouseEvent e) {
        if (e.button != 1)
            return;
        if (!(e.widget instanceof Control))
            return;
        Control control = (Control) e.widget;
        oldBounds = control.getBounds();
        active = true;
    }

    @Override
    protected void mouseUp2(MouseEvent e, MouseEvent d) {
        active = false;
    }

    public void mouseMove(MouseEvent e) {
        // System.out.println(e);
        // if (e.button != 1) return;
        if ((e.stateMask & SWT.BUTTON1) == 0)
            return;
        if (!active)
            return;

        MouseEvent d = getPrevious(1);

        assert d.widget instanceof Control;
        Control control = (Control) d.widget;

        Rectangle bounds = control.getBounds();
        int dx = e.x - d.x;
        int dy = e.y - d.y;
        bounds.x += dx;
        bounds.y += dy;
        control.setBounds(bounds);

        move(dx, dy);
    }

    protected void move(int dx, int dy) {
    }

    public void keyPressed(KeyEvent e) {
        if (!active)
            return;

        boolean cancel = false;

        switch (e.keyCode) {
        case (int) SWT.ESC: // XXX
            cancel = true;
            active = false;
        }

        if (cancel) {
            MouseEvent d = getPrevious(1);

            assert d.widget instanceof Control;
            Control control = (Control) d.widget;

            control.setBounds(oldBounds);
        }
    }

    public void keyReleased(KeyEvent e) {
    }

}
