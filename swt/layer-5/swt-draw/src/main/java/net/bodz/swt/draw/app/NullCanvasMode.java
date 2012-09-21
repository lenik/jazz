package net.bodz.swt.draw.app;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;

public class NullCanvasMode
        implements ICanvasMode {

    @Override
    public void mouseDown(MouseEvent e) {
    }

    @Override
    public void mouseUp(MouseEvent e, MouseEvent d) {
    }

    @Override
    public void mouseMove(MouseEvent e, MouseEvent d) {
    }

    @Override
    public void mouseDoubleClick(MouseEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static final NullCanvasMode INSTANCE = new NullCanvasMode();

}
