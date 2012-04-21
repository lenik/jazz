package net.bodz.swt.state;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;

public interface SWTState
        extends State {

    SWTState onMouseDown(MouseEvent e);

    SWTState onMouseUp(MouseEvent e, MouseEvent d);

    /**
     * 
     * @param e
     * @param d
     *            null if no mouse down.
     * @return
     */
    SWTState onMouseMove(MouseEvent e, MouseEvent d);

    SWTState onMouseDoubleClick(MouseEvent e);

    SWTState onKeyPressed(KeyEvent e);

    SWTState onKeyReleased(KeyEvent e);

}
