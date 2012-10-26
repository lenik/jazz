package net.bodz.swt.c.control;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;

public interface IMouseKeyListener
        extends IStructuredMouseListener {

    /**
     * @param e
     *            Mouse double click event.
     * @return
     * @see org.eclipse.swt.events.MouseListener
     */
    void mouseDoubleClick(MouseEvent e);

    /**
     * Sent when a key is pressed on the system keyboard.
     * 
     * @param e
     *            an event containing information about the key press
     */
    void keyDown(KeyEvent e);

    /**
     * Sent when a key is released on the system keyboard.
     * 
     * @param e
     *            an event containing information about the key release
     */
    void keyUp(KeyEvent e);

}
