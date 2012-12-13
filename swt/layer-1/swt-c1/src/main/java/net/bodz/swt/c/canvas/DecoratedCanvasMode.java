package net.bodz.swt.c.canvas;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;

import net.bodz.bas.t.model.AbstractDecorator;

public abstract class DecoratedCanvasMode
        extends AbstractDecorator<ICanvasMode>
        implements ICanvasMode {

    private static final long serialVersionUID = 1L;

    protected DecoratedCanvasMode() {
        super(NullCanvasMode.INSTANCE);
    }

    public DecoratedCanvasMode(ICanvasMode previous) {
        super(previous);
    }

    @Override
    public void mouseDown(MouseEvent e) {
        getWrapped().mouseDown(e);
    }

    @Override
    public void mouseUp(MouseEvent e, MouseEvent d) {
        getWrapped().mouseUp(e, d);
    }

    @Override
    public void mouseMove(MouseEvent e, MouseEvent d) {
        getWrapped().mouseMove(e, d);
    }

    @Override
    public void mouseDoubleClick(MouseEvent e) {
        getWrapped().mouseDoubleClick(e);
    }

    @Override
    public void keyDown(KeyEvent e) {
        getWrapped().keyDown(e);
    }

    @Override
    public void keyUp(KeyEvent e) {
        getWrapped().keyUp(e);
    }

}
