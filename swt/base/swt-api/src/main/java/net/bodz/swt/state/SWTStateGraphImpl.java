package net.bodz.swt.state;

import java.util.Map;

import net.bodz.bas.fsm.base.IState;
import net.bodz.bas.fsm.base.StateGraphImpl;
import net.bodz.swt.ex.HistoryMouseListener;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;

public class SWTStateGraphImpl
        extends StateGraphImpl
        implements ISWTStateGraph, MouseListener, MouseMoveListener, KeyListener {

    private static final long serialVersionUID = 2084698255485360902L;

    public SWTStateGraphImpl() {
        this(new SWTDummyState(), null);
    }

    public SWTStateGraphImpl(ISWTState start) {
        this(start, null);
    }

    public SWTStateGraphImpl(SWTContext context) {
        this(new SWTDummyState(), context);
    }

    public SWTStateGraphImpl(ISWTState start, SWTContext context) {
        super(start, context);
        assert start != null;
    }

    public SWTStateGraphImpl(Map<Object, IState> registry, Object startKey) {
        super(registry, startKey);
    }

    public SWTStateGraphImpl(Map<Object, IState> registry, Object startKey, SWTContext context) {
        super(registry, startKey, context);
    }

    // -o StateGraph

    @Override
    public SWTContext context() {
        return context();
    }

    @Override
    public ISWTState current() {
        return (ISWTState) super.current();
    }

    @Override
    public ISWTState get(Object key) {
        return (ISWTState) super.get(key);
    }

    @Override
    public ISWTState pop() {
        return (ISWTState) super.pop();
    }

    @Override
    public ISWTState recv(Object message) {
        return (ISWTState) super.recv(message);
    }

    // -o MouseListener

    private HistoryMouseListener hm = new HistoryMouseListener() {

        @Override
        protected void mouseDown2(MouseEvent e) {
            SWTStateGraphImpl.this.mouseDown2(e);
        }

        @Override
        protected void mouseUp2(MouseEvent e, MouseEvent d) {
            SWTStateGraphImpl.this.mouseUp2(e, d);
        }

        @Override
        public void mouseDoubleClick(MouseEvent e) {
            SWTStateGraphImpl.this.mouseDoubleClick2(e);
        }

    };

    public final void mouseDown(MouseEvent e) {
        hm.mouseDown(e);
    }

    public final void mouseUp(MouseEvent e) {
        hm.mouseUp(e);
    }

    public final void mouseDoubleClick(MouseEvent e) {
        hm.mouseDoubleClick(e);
    }

    protected void mouseDown2(MouseEvent e) {
        ISWTState state = (ISWTState) current;
        ISWTState target = state.onMouseDown(e);
        if (state != target)
            jump(target);
    }

    protected void mouseUp2(MouseEvent e, MouseEvent d) {
        ISWTState state = (ISWTState) current;
        ISWTState target = state.onMouseUp(e, d);
        if (state != target)
            jump(target);
    }

    protected void mouseDoubleClick2(MouseEvent e) {
        ISWTState state = (ISWTState) current;
        ISWTState target = state.onMouseDoubleClick(e);
        if (state != target)
            jump(target);
    }

    // -o MouseMoveListener

    public void mouseMove(MouseEvent e) {
        // d may be null.
        MouseEvent d = hm.getPrevious(e.button);

        ISWTState state = (ISWTState) current;
        ISWTState target = state.onMouseMove(e, d);
        if (state != target)
            jump(target);
    }

    // -o KeyListener

    public void keyPressed(KeyEvent e) {
        ISWTState state = (ISWTState) current;
        ISWTState target = state.onKeyPressed(e);
        if (state != target)
            jump(target);
    }

    public void keyReleased(KeyEvent e) {
        ISWTState state = (ISWTState) current;
        ISWTState target = state.onKeyReleased(e);
        if (state != target)
            jump(target);
    }

}
