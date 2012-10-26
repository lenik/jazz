package net.bodz.swt.c.control;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.widgets.Event;

public abstract class StructuredMouseAdapter
        // extends MouseAdapter
        implements MouseListener, MouseMoveListener, IStructuredMouseListener {

    List<MouseEvent> stack;

    public StructuredMouseAdapter() {
        stack = new ArrayList<MouseEvent>();
    }

    protected MouseEvent getLastMouseDownEvent() {
        if (stack.isEmpty())
            return null;
        else
            return stack.get(stack.size() - 1);
    }

    @Override
    public final void mouseDown(MouseEvent e) {
        stack.add(e);
        _mouseDown(e);
    }

    @Override
    public final void mouseMove(MouseEvent e) {
        if (stack.isEmpty()) {
            mouseMove(e, null);
        } else {
            MouseEvent last = stack.get(stack.size() - 1);
            mouseMove(e, last);
        }
    }

    /**
     * 从最近一次的按下事件 r 开始：
     * <ul>
     * <li>如果 r 和放开事件同一个按钮，则抵消这两个事件；
     * <li>如果不是同一个按钮，则构造一个 r 的补并先触发这个补，并继续往前查找按下事件。
     * </ul>
     */
    @Override
    public final void mouseUp(MouseEvent e) {
        int upButton = e.button;
        int size = stack.size();
        while (size > 0) {
            MouseEvent removedEvent = stack.remove(--size);
            if (removedEvent.button == upButton) {
                // mouse-up the same button.
                mouseUp(e, removedEvent);
                return;
            } else {
                // auto release previous mouse-down event.
                Event pseudoEvent = new Event();
                pseudoEvent.widget = e.widget;
                pseudoEvent.display = e.display;
                pseudoEvent.time = e.time;
                pseudoEvent.data = e.data;
                pseudoEvent.x = e.x;
                pseudoEvent.y = e.y;
                pseudoEvent.button = removedEvent.button;
                pseudoEvent.stateMask = e.stateMask;
                pseudoEvent.count = e.count;

                MouseEvent pseduoMouseDownEvent = new MouseEvent(pseudoEvent);
                mouseUp(removedEvent, pseduoMouseDownEvent);
            }
        }
    }

    // Default Implementation

    protected void _mouseDown(MouseEvent e) {
    }

    @Override
    public void mouseMove(MouseEvent e, MouseEvent mouseDownEvent) {
    }

    @Override
    public void mouseUp(MouseEvent e, MouseEvent mouseDownEvent) {
    }

    @Override
    public void mouseDoubleClick(MouseEvent e) {
    }

}
