package net.bodz.swt.ex;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.types.LinkedStack;
import net.bodz.bas.types.Stack;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;

public abstract class HistoryMouseListener implements MouseListener {

    protected Map<Integer, Stack<MouseEvent>> stacks;

    public HistoryMouseListener() {
        stacks = new HashMap<Integer, Stack<MouseEvent>>();
    }

    protected MouseEvent save(MouseEvent e) {
        return e;
    }

    public final void mouseDown(MouseEvent e) {
        Stack<MouseEvent> stack = getStack(e.button);

        MouseEvent de = save(e);
        stack.push(de);

        mouseDown2(e);
    }

    public final void mouseUp(MouseEvent e) {
        Stack<MouseEvent> stack = stacks.get(e.button);

        // Ignore "unbalanced" mouseUp (s)
        if (stack == null || stack.isEmpty())
            return;

        MouseEvent d = stack.pop();

        mouseUp2(e, d);
    }

    public Stack<MouseEvent> getStack(int button) {
        Stack<MouseEvent> stack = stacks.get(button);
        if (stack == null) {
            stack = new LinkedStack<MouseEvent>();
            stacks.put(button, stack);
        }
        return stack;
    }

    public MouseEvent getPrevious(int button) {
        Stack<MouseEvent> stack = getStack(button);
        if (stack.isEmpty())
            return null;
        return stack.top();
    }

    protected void mouseDown2(MouseEvent e) {
    }

    protected void mouseUp2(MouseEvent e, MouseEvent d) {
    }

    public void mouseDoubleClick(MouseEvent e) {
    }
}
