package net.bodz.swt.c.control;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;

public class MouseKeyBridge
        extends MouseKeyAdapter {

    final IMouseKeyListener target;

    public MouseKeyBridge(IMouseKeyListener target) {
        if (target == null)
            throw new NullPointerException("target");
        this.target = target;
    }

    @Override
    public void keyDown(KeyEvent e) {
        target.keyDown(e);
    }

    @Override
    public void keyUp(KeyEvent e) {
        target.keyUp(e);
    }

    @Override
    protected void _mouseDown(MouseEvent e) {
        target.mouseDown(e);
    }

    @Override
    public void mouseMove(MouseEvent e, MouseEvent mouseDownEvent) {
        target.mouseMove(e, mouseDownEvent);
    }

    @Override
    public void mouseUp(MouseEvent e, MouseEvent mouseDownEvent) {
        target.mouseUp(e, mouseDownEvent);
    }

    @Override
    public void mouseDoubleClick(MouseEvent e) {
        target.mouseDoubleClick(e);
    }

}
