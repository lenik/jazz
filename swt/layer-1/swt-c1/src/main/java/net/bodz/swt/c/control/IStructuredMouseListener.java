package net.bodz.swt.c.control;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.internal.SWTEventListener;

public interface IStructuredMouseListener
        extends SWTEventListener {

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
     * @param mouseDownEvent
     *            The last mouse down event. <code>null</code> if no mouse down.
     * @return
     * @see org.eclipse.swt.events.MouseListener
     */
    void mouseUp(MouseEvent e, MouseEvent mouseDownEvent);

    /**
     * @param e
     *            Mouse move event.
     * @param mouseDownEvent
     *            The last mouse down event. <code>null</code> if no mouse down.
     * @return
     * @see org.eclipse.swt.events.MouseMoveListener
     */
    void mouseMove(MouseEvent e, MouseEvent mouseDownEvent);

}
