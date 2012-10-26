package net.bodz.swt.c.control;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;

public class MouseKeyAdapter
        extends StructuredMouseAdapter
        implements IMouseKeyListener, KeyListener {

    @Override
    public final void keyPressed(KeyEvent e) {
        keyDown(e);
    }

    @Override
    public final void keyReleased(KeyEvent e) {
        keyUp(e);
    }

    @Override
    public void keyDown(KeyEvent e) {
    }

    @Override
    public void keyUp(KeyEvent e) {
    }

}
