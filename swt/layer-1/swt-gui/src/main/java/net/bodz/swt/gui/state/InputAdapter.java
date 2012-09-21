package net.bodz.swt.gui.state;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;

import net.bodz.bas.collection.map.IndexMap;

public class InputAdapter
        implements MouseListener, MouseMoveListener, KeyListener {

    private IInputListener inputListener;
    private IndexMap<MouseEvent> buttonDownEventMap;

    public InputAdapter(IInputListener inputListener) {
        if (inputListener == null)
            throw new NullPointerException("inputListener");
        this.inputListener = inputListener;
        this.buttonDownEventMap = new IndexMap<MouseEvent>();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        inputListener.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        inputListener.keyReleased(e);
    }

    @Override
    public void mouseMove(MouseEvent e) {
        MouseEvent downEvent = buttonDownEventMap.get(e.button);
        inputListener.mouseMove(e, downEvent);
    }

    @Override
    public void mouseDoubleClick(MouseEvent e) {
        inputListener.mouseDoubleClick(e);
    }

    @Override
    public void mouseDown(MouseEvent e) {
        buttonDownEventMap.put(e.button, e);
        inputListener.mouseDown(e);
    }

    @Override
    public void mouseUp(MouseEvent e) {
        MouseEvent downEvent = buttonDownEventMap.get(e.button);
        inputListener.mouseUp(e, downEvent);
    }

}
