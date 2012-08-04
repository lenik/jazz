package net.bodz.swt.gui.state;

import net.bodz.bas.fsm.base.IState;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;

public interface ISWTState
        extends IState {

    ISWTState onMouseDown(MouseEvent e);

    ISWTState onMouseUp(MouseEvent e, MouseEvent d);

    /**
     * 
     * @param e
     * @param d
     *            null if no mouse down.
     * @return
     */
    ISWTState onMouseMove(MouseEvent e, MouseEvent d);

    ISWTState onMouseDoubleClick(MouseEvent e);

    ISWTState onKeyPressed(KeyEvent e);

    ISWTState onKeyReleased(KeyEvent e);

}
