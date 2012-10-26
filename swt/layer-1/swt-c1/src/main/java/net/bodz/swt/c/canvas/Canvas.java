package net.bodz.swt.c.canvas;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;

import net.bodz.swt.c.control.MouseKeyAdapter;

public class Canvas
        extends org.eclipse.swt.widgets.Canvas {

    ICanvasMode mode = NullCanvasMode.INSTANCE;

    public Canvas(Composite parent, int style) {
        super(parent, style);
        MouseKeyBridge bridge = new MouseKeyBridge();
        addMouseListener(bridge);
        addMouseMoveListener(bridge);
        addKeyListener(bridge);
    }

    public ICanvasMode getMode() {
        return mode;
    }

    public void setMode(ICanvasMode mode) {
        this.mode = mode;
    }

    private class MouseKeyBridge
            extends MouseKeyAdapter {

        @Override
        public void keyDown(KeyEvent e) {
            mode.keyDown(e);
        }

        @Override
        public void keyUp(KeyEvent e) {
            mode.keyUp(e);
        }

        @Override
        protected void _mouseDown(MouseEvent e) {
            mode.mouseDown(e);
        }

        @Override
        public void mouseMove(MouseEvent e, MouseEvent mouseDownEvent) {
            mode.mouseMove(e, mouseDownEvent);
        }

        @Override
        public void mouseUp(MouseEvent e, MouseEvent mouseDownEvent) {
            mode.mouseUp(e, mouseDownEvent);
        }

        @Override
        public void mouseDoubleClick(MouseEvent e) {
            mode.mouseDoubleClick(e);
        }

    }

}
