package net.bodz.swt.gui.state;

import java.util.EventListener;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;

public interface IInputListener
        extends EventListener {

    /**
     * @param e
     *            Mouse down event.
     * @return
     * @see MouseListener
     */
    void mouseDown(MouseEvent e);

    /**
     * @param e
     *            Mouse up event.
     * @param d
     *            The last mouse down event. <code>null</code> if no mouse down.
     * @return
     * @see org.eclipse.swt.events.MouseListener
     */
    void mouseUp(MouseEvent e, MouseEvent d);

    /**
     * @param e
     *            Mouse move event.
     * @param d
     *            The last mouse down event. <code>null</code> if no mouse down.
     * @return
     * @see org.eclipse.swt.events.MouseMoveListener
     */
    void mouseMove(MouseEvent e, MouseEvent d);

    /**
     * @param e
     *            Mouse double click event.
     * @return
     * @see org.eclipse.swt.events.MouseListener
     */
    void mouseDoubleClick(MouseEvent e);

    /**
     * @see org.eclipse.swt.events.KeyListener
     */
    void keyPressed(KeyEvent e);

    /**
     * @see org.eclipse.swt.events.KeyListener
     */
    void keyReleased(KeyEvent e);

}
